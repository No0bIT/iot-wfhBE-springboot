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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String password;
	private String sdt;
	private String sex;
	private LocalDate date;
	private int age;	
	private float height;
	private float weight;
	private float bmi;
	 
	private float strideLength;
//	@JsonIgnoreProperties("customer")
	@JsonIgnore
    @OneToMany(mappedBy = "customer")
	List<Daily> dailys= new ArrayList<>();
	

//	@JsonIgnoreProperties("customer")
	@JsonIgnore
    @OneToMany(mappedBy = "customer")
	List<Notification> notifications= new ArrayList<>();


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSdt() {
		return sdt;
	}


	public void setSdt(String sdt) {
		this.sdt = sdt;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public float getStrideLength() {
		return strideLength;
	}


	public void setStrideLength(float strideLength) {
		this.strideLength = strideLength;
	}

	public float getHeight() {
		return height;
	}


	public void setHeight(float height) {
		this.height = height;
	}


	public float getWeight() {
		return weight;
	}


	public void setWeight(float weight) {
		this.weight = weight;
	}


	public float getBmi() {
		return bmi;
	}


	public void setBmi(float bmi) {
		this.bmi = bmi;
	}


	public List<Daily> getDailys() {
		return dailys;
	}


	public void setDailys(List<Daily> dailys) {
		this.dailys = dailys;
	}


	public List<Notification> getNotifications() {
		return notifications;
	}


	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	
}
