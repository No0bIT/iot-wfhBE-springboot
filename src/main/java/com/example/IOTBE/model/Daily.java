package com.example.IOTBE.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Table
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Daily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int totalStep;
	private float totalDistance;
	private float totalCalo;
	private String weather;
	private int humidity;
	private double temperature;
	private int dayOTW;
	private LocalDate date;
	private int predictionStep;
	private float predictionDistance;
	
//    @JsonIgnoreProperties("dailys")
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    
//	@JsonIgnoreProperties("daily")
	@JsonIgnore
    @OneToMany(mappedBy = "daily")
	List<Step> steps= new ArrayList<>();


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTotalStep() {
		return totalStep;
	}


	public void setTotalStep(int totalStep) {
		this.totalStep = totalStep;
	}


	public float getTotalDistance() {
		return totalDistance;
	}


	public void setTotalDistance(float totalDistance) {
		this.totalDistance = totalDistance;
	}


	public float getTotalCalo() {
		return totalCalo;
	}


	public void setTotalCalo(float totalCalo) {
		this.totalCalo = totalCalo;
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}


	public int getHumidity() {
		return humidity;
	}


	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


	public int getDayOTW() {
		return dayOTW;
	}


	public void setDayOTW(int dayOTW) {
		this.dayOTW = dayOTW;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getPredictionStep() {
		return predictionStep;
	}


	public void setPredictionStep(int predictionStep) {
		this.predictionStep = predictionStep;
	}


	public float getPredictionDistance() {
		return predictionDistance;
	}


	public void setPredictionDistance(float predictionDistance) {
		this.predictionDistance = predictionDistance;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<Step> getSteps() {
		return steps;
	}


	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	
	
}
