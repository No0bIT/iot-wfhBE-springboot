package com.example.IOTBE.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.IOTBE.subModel.WeatherResponse;

@Service
public class WeatherService {

    public WeatherResponse getWeatherData() {
    	String apiUrl = "http://api.weatherapi.com/v1/current.json?key=a6d2820a8ef743f8b56164253232111&q=hanoi&aqi=no";
    	WebClient.Builder builder= WebClient.builder();
    	
    	WeatherResponse weatherData = builder.build().get().uri(apiUrl).retrieve().bodyToMono(WeatherResponse.class).block();
        return weatherData;
    }
}
