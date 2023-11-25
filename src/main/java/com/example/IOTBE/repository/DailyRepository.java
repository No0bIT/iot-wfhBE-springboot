package com.example.IOTBE.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.IOTBE.model.Daily;


public interface DailyRepository extends JpaRepository<Daily, Integer> {
}
