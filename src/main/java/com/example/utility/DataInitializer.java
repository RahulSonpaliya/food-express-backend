package com.example.utility;

import org.springframework.stereotype.Component;

import com.example.entity.AppRegion;
import com.example.repository.AppRegionRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class DataInitializer {

	@Autowired
	private AppRegionRepository appRegionRepository;

	@PostConstruct
	public void postConstruct() {
		if (appRegionRepository.count() == 0) {
			var usa = new AppRegion("+1", "United States", "US", "USA");
			appRegionRepository.save(usa);
			var india = new AppRegion("+91", "India", "IN", "IND");
			appRegionRepository.save(india);
		}
	}
}
