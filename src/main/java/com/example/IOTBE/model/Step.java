package com.example.IOTBE.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Table
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Step {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private LocalDateTime date;
	private int step;
	
	
//    @JsonIgnoreProperties("steps")
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "daily_id")
    private Daily daily;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public int getStep() {
		return step;
	}


	public void setStep(int step) {
		this.step = step;
	}


	public Daily getDaily() {
		return daily;
	}


	public void setDaily(Daily daily) {
		this.daily = daily;
	}
    
    
    
}
