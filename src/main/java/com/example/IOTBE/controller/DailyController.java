package com.example.IOTBE.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.IOTBE.model.Customer;
import com.example.IOTBE.model.Daily;
import com.example.IOTBE.repository.CustomerRepository;
import com.example.IOTBE.repository.DailyRepository;

@RestController
@RequestMapping("/api/daily")
public class DailyController {

	
	@Autowired
	DailyRepository dailyRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/getToday/{idUser}")
	public Daily getOne(@PathVariable int idUser)
	{
		Customer customer= customerRepository.getById(idUser);
		Daily daily= customer.getDailys().stream() // Stream từ danh sách Daily của Customer
                .max(Comparator.comparingLong(Daily::getId)) // Tìm Daily có id lớn nhất
                .orElse(null);
		return daily;
	}
	
	
	@GetMapping("/get7day/{idUser}")
	public List<Daily> get7day(@PathVariable int idUser)
	{
		Customer customer= customerRepository.getById(idUser);
		List<Daily> dailys= customer.getDailys();
		List<Daily> dailyAns= new ArrayList<>();
		Collections.sort(dailys, Comparator.comparingInt(Daily::getId).reversed());
		for(Daily daily:dailys) {
			dailyAns.add(daily);
			if(dailyAns.size()==7)
				break;
		}
		int x=dailyAns.size();
		if(x<7){
			for(int i=0;i<7-x;i++)
			{
				Daily xDaily=  new Daily();
				dailyAns.add(xDaily);
			}
		}
		return dailyAns;
	}
}
