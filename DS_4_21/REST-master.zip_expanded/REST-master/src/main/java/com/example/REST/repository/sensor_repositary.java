package com.example.REST.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.REST.model.sensor;

public interface sensor_repositary extends JpaRepository<sensor, Integer> {
	
	sensor findBylocation(String location);

}
