package com.example.IOTBE.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.IOTBE.model.Customer;
import com.example.IOTBE.model.Daily;
import com.example.IOTBE.model.Notification;
import com.example.IOTBE.model.Step;
import com.example.IOTBE.repository.CustomerRepository;
import com.example.IOTBE.repository.NotificationRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class NotificationScheduler {

    
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    NotificationRepository notificationService;
    @Autowired
    NotificationRepository notificationRepository;
    
    
    @Transactional
    @Scheduled(cron = "0 0,30 9,10,11,16,17,18 * * *") 
    public void notExerciseForTooLong() {
    	List<Customer> customers =customerRepository.findAll();
    	for(Customer customer:customers) {
    		Daily daily= customer.getDailys().stream() // Stream từ danh sách Daily của Customer
	                .max(Comparator.comparingLong(Daily::getId)) // Tìm Daily có id lớn nhất
	                .orElse(null);
    		List<Step> steps= daily.getSteps();
    		Collections.sort(steps, Comparator.comparingInt(Step::getId).reversed());
    		
    		int totalStep = 0;
            int count = 0;
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime twoHoursAgo = currentTime.minusHours(2);
            
            
            for (Step step : steps) {
                LocalDateTime stepTime = step.getDate();

                // Kiểm tra xem stepTime có nằm trong khoảng 2 tiếng không
                if (stepTime.isAfter(twoHoursAgo) && stepTime.isBefore(currentTime)) {
                	totalStep += step.getStep();
                	count++;
                } else if (stepTime.isBefore(twoHoursAgo)) {
                    // Vì danh sách đã sắp xếp giảm dần theo thời gian,
                    // nếu stepTime trước khoảng 2 tiếng, có thể dừng vòng lặp
                    break;
                }
            }
            
            if(totalStep<20 && count> 90)
            {
            	Notification notification = new Notification();
        		notification.setContent("Bạn đã không vận động quá lâu, hãy vận động thân thể bằng cách đi lại xung quanh nào");
        		notification.setTime(LocalDateTime.now());
        		
        		notification.setCustomer(customer);
        		List<Notification> notifications= new ArrayList<>();
        		notifications.add(notification);
        		customer.setNotifications(notifications);
        	    
        	    notificationRepository.save(notification);
        		customerRepository.save(customer);
            }
    	}
    }

}
