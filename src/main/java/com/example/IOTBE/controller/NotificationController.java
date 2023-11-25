package com.example.IOTBE.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IOTBE.model.Customer;
import com.example.IOTBE.model.Notification;
import com.example.IOTBE.repository.CustomerRepository;
import com.example.IOTBE.repository.NotificationRepository;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/getAll/{idUser}")
	public List<Notification> getAll(@PathVariable int idUser){
		Customer customer= customerRepository.getById(idUser);
		List<Notification> notifications= customer.getNotifications();
		Collections.sort(notifications, Comparator.comparing(Notification::getId).reversed());
		return notifications;
	}
	
	@GetMapping("/get10/{idUser}")
	public List<Notification> get10(@PathVariable int idUser){
		Customer customer= customerRepository.getById(idUser);
		List<Notification> notifications= customer.getNotifications();
		Collections.sort(notifications, Comparator.comparing(Notification::getId).reversed());
		List<Notification> notificationsAns=new ArrayList<>();
		for(Notification notification: notifications) {
			notificationsAns.add(notification);
			if(notificationsAns.size()==10)
				break;
		}
		return notificationsAns;
	}
	
	@PostMapping("/read/{idNoti}")
	public Notification read(@PathVariable int idNoti)
	{
		Notification notification = notificationRepository.getById(idNoti);
		notification.setStatus(1);
		notificationRepository.save(notification);
		return notification;
	}
}
