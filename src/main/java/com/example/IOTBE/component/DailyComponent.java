package com.example.IOTBE.component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.IOTBE.model.Customer;
import com.example.IOTBE.model.Daily;
import com.example.IOTBE.repository.CustomerRepository;
import com.example.IOTBE.repository.DailyRepository;
import com.example.IOTBE.repository.StepRepository;
import com.example.IOTBE.service.PredictionService;
import com.example.IOTBE.service.WeatherService;
import com.example.IOTBE.subModel.WeatherResponse;

@Component
public class DailyComponent {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	DailyRepository dailyRepository;
	@Autowired
	StepRepository stepRepository;
	@Autowired
	WeatherService weatherService;
	@Autowired 
	PredictionService predictionService; 
	
	@Transactional
	@Scheduled(cron = "0 0 0 * * *") 
    public void createDaily() {       			
		List<Customer> customers= customerRepository.findAll();		
		WeatherResponse weatherResponse = weatherService.getWeatherData();
		for(Customer cus:customers) {
			//add dữ liệu ngày hôm trước vào dataTraning
			Daily dailyOld= cus.getDailys().stream() // Stream từ danh sách Daily của Customer
	                .max(Comparator.comparingLong(Daily::getId)) // Tìm Daily có id lớn nhất
	                .orElse(null);
			try {
				predictionService.addDataTrainCus(cus.getId(), dailyOld);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Daily daily= new Daily();
			daily.setPredictionStep(10000);
			daily.setPredictionDistance(cus.getStrideLength()*10000 );
			
			LocalDate localDate= LocalDate.now();
			daily.setDayOTW(localDate.getDayOfWeek().getValue());
			daily.setDate(LocalDate.now());
			daily.setHumidity(weatherResponse.getCurrent().getHumidity());
			daily.setTemperature(weatherResponse.getCurrent().getTemperatureCelsius());
			daily.setWeather(weatherResponse.getCurrent().getCondition().getText());
			int stepPrediction=0;
			try {
				stepPrediction=predictionService.getPrediction(cus.getId(), daily);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(stepPrediction>5000)
				if(stepPrediction>10000)
					if(stepPrediction>15000)
						if(stepPrediction>20000)
							stepPrediction=stepPrediction*1;
						else
							stepPrediction=(int) (stepPrediction*1.1f);
					else 
						stepPrediction= (int) (stepPrediction*1.2);
				else 
					stepPrediction=(int)(stepPrediction*1.3);
			else 
				stepPrediction=(int)(stepPrediction*1.4);
			
			
			daily.setPredictionStep(stepPrediction);
			daily.setPredictionDistance(stepPrediction*cus.getStrideLength());
						
			daily.setCustomer(cus);
			dailyRepository.save(daily);	
		}
		
        System.out.println("Creating daily ");
        
    }
}
