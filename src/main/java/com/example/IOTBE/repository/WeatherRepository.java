package com.example.IOTBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IOTBE.trainingModel.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Integer>{

}
