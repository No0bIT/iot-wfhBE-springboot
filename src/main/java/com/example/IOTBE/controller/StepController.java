package com.example.IOTBE.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IOTBE.model.Customer;
import com.example.IOTBE.model.Daily;
import com.example.IOTBE.model.Notification;
import com.example.IOTBE.model.Step;
import com.example.IOTBE.repository.CustomerRepository;
import com.example.IOTBE.repository.DailyRepository;
import com.example.IOTBE.repository.NotificationRepository;
import com.example.IOTBE.repository.StepRepository;

@RestController
@RequestMapping("/api/step")
public class StepController {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	DailyRepository dailyRepository;
	@Autowired
	StepRepository stepRepository;
	@Autowired
	NotificationRepository notificationRepository;
	
	@PostMapping("/add/{id}")
	public Daily addStep(@PathVariable int id,@RequestBody Step step)
	{
		step.setDate(LocalDateTime.now());
		Customer customer= customerRepository.getById(id);
		Daily daily= customer.getDailys().stream() // Stream từ danh sách Daily của Customer
                .max(Comparator.comparingLong(Daily::getId)) // Tìm Daily có id lớn nhất
                .orElse(null);
		int countStep=step.getStep()-daily.getTotalStep();
		countStep = countStep>=0?countStep:0;
		step.setStep(countStep);
		
		int checkNotification =daily.getTotalStep()+step.getStep();
		if(checkNotification>=daily.getPredictionStep())
		{
			if(daily.getTotalStep()<daily.getPredictionStep())
			{
				Notification notification = new Notification();
        		notification.setContent("Bạn đã đạt được mục tiêu của ngày hôm nay "+ daily.getPredictionStep()+" bước thật tuyệt vời!");
        		notification.setTime(LocalDateTime.now());
        		
        		notification.setCustomer(customer);
        		List<Notification> notifications= new ArrayList<>();
        		notifications.add(notification);
        		customer.setNotifications(notifications);
        	    
        	    notificationRepository.save(notification);
        		customerRepository.save(customer);
			}
		}
		daily.setTotalStep(checkNotification);
		daily.setTotalDistance(daily.getTotalStep()*customer.getStrideLength());
		daily.setTotalCalo((float) (daily.getTotalDistance()*customer.getWeight()*0.57));
		// connect
		step.setDaily(daily);
		
		stepRepository.save(step);
//		List<Step> steps= daily.getSteps();
//		steps.add(step);
//		daily.setSteps(steps);
//		// luu
//		dailyRepository.save(daily);
		return daily;
	}
}
