package com.example.IOTBE.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class Notification {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String content;
	private int status;
	private LocalDateTime time;
//	@JsonIgnoreProperties("notifications")
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public Customer getCustomer() {
		return customer;
	}




	public void setCustomer(Customer customer) {
		this.customer = customer;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public LocalDateTime getTime() {
		return time;
	}




	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
