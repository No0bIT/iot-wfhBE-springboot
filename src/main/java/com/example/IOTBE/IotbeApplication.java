package com.example.IOTBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IotbeApplication {


	public static void main(String[] args) {
		SpringApplication.run(IotbeApplication.class, args);
	}

}
