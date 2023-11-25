package com.example.IOTBE.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IOTBE.model.Customer;
import com.example.IOTBE.model.Daily;
import com.example.IOTBE.model.Notification;
import com.example.IOTBE.repository.CustomerRepository;
import com.example.IOTBE.repository.DailyRepository;
import com.example.IOTBE.repository.NotificationRepository;
import com.example.IOTBE.service.PredictionService;
import com.example.IOTBE.service.WeatherService;
import com.example.IOTBE.subModel.WeatherResponse;

@RestController
@RequestMapping("/api/user")
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	DailyRepository dailyRepository;
	@Autowired
	WeatherService weatherService;
	@Autowired 
	NotificationRepository notificationRepository;
	@Autowired
	PredictionService predictionService;
			
	@PostMapping("/create")
	public int createCus(@RequestBody Customer bodyCustomer) {
		List<Customer> customers = customerRepository.findAll();
		for(Customer customer :customers) {
			if(customer.getSdt().equals(bodyCustomer.getSdt()))
				return -1;
		}
		
		Notification notification = new Notification();
		notification.setContent("Chào mừng "+bodyCustomer.getName()+" đã tham gia WFH");
		notification.setTime(LocalDateTime.now());
		
		
		WeatherResponse weatherResponse = weatherService.getWeatherData();		
		float strideLength = 0.7f;
		bodyCustomer.setStrideLength(strideLength);
		bodyCustomer.setBmi(bodyCustomer.getWeight()/(bodyCustomer.getHeight()*bodyCustomer.getHeight()));
		
		Daily daily= new Daily();
		if(bodyCustomer.getDate()==null)		
			bodyCustomer.setDate(LocalDate.now());			
		daily.setPredictionStep(10000);
		daily.setPredictionDistance(bodyCustomer.getStrideLength()*10000 );
		
		LocalDate localDate= LocalDate.now();
		daily.setDayOTW(localDate.getDayOfWeek().getValue());
		daily.setDate(localDate);
		daily.setHumidity(weatherResponse.getCurrent().getHumidity());
		daily.setTemperature(weatherResponse.getCurrent().getTemperatureCelsius());
		daily.setWeather(weatherResponse.getCurrent().getCondition().getText());
		
		
		customerRepository.save(bodyCustomer);
		
		daily.setCustomer(bodyCustomer);
		List<Daily> dailys= new ArrayList<>();
		dailys.add(daily);
		bodyCustomer.setDailys(dailys);
		
		
		dailyRepository.save(daily);
		
		
	    notificationRepository.save(notification);
	    
		notification.setCustomer(bodyCustomer);
		
		List<Notification> notifications= new ArrayList<>();
		notifications.add(notification);
	    bodyCustomer.setNotifications(notifications);	    
		customerRepository.save(bodyCustomer);	
		predictionService.createDataTrainCus(bodyCustomer.getId());
		
		return bodyCustomer.getId();
	}
	
	
	@GetMapping("/get/{id}")
	public Customer getCus(@PathVariable int id)
	{
		return customerRepository.getById(id);
	}
	
	@PostMapping("/edit")
	public Customer editCus(@RequestBody Customer customer)
	{
		Customer cusdb=customerRepository.getById(customer.getId());
		cusdb.setName(customer.getName());
		cusdb.setPassword(customer.getPassword());
		cusdb.setSdt(customer.getSdt());
		cusdb.setSex(customer.getSex());
		cusdb.setAge(customer.getAge());
		cusdb.setHeight(customer.getHeight());
		cusdb.setWeight(customer.getWeight());
		cusdb.setBmi(customer.getWeight()/(customer.getHeight()*customer.getHeight()));
		cusdb.setStrideLength(0.7f);
		customerRepository.save(cusdb);
		return customerRepository.getById(customer.getId());
	}
	@PostMapping("/login")
	public int login(@RequestBody Customer customer) {
		List<Customer> customers= customerRepository.findAll();		
		
		for(Customer cus:customers) {
			if(cus.getSdt().equals(customer.getSdt())) {
				if(cus.getPassword().equals(customer.getPassword()))
					return cus.getId();
				return 0;
			}
		}
		
		return -1;
	}
}
