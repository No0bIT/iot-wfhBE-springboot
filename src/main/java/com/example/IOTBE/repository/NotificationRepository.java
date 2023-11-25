package com.example.IOTBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IOTBE.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{

}
