package com.example.IOTBE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.IOTBE.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
