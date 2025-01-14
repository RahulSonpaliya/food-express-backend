package com.example.utility;

import com.example.entity.AppRegion;
import com.example.entity.Category;
import com.example.exception.JobPortalException;
import com.example.model.CategoryStatus;
import com.example.repository.AppRegionRepository;
import com.example.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer {

    @Autowired
    private AppRegionRepository appRegionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void postConstruct() throws JobPortalException {
        if (appRegionRepository.count() == 0) {
            var usa = new AppRegion("+1", "United States", "US", "USA");
            appRegionRepository.save(usa);
            var india = new AppRegion("+91", "India", "IN", "IND");
            appRegionRepository.save(india);
        }
        if (categoryRepository.count() == 0) {
            var food = new Category(1L, "Food", "restaurant.png", "Food & Bevereges", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(food);
            var fashion = new Category(2L, "Fashion", "dress.png", "Fashion", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(fashion);
            var electronics = new Category(3L, "Electronics", "responsive.png", "Electronics", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(electronics);
            var groceries = new Category(4L, "Groceries", "grocery.png", "Groceries", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(groceries);
            var medicines = new Category(5L, "Medicines", "drugs.png", "Medicines", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(medicines);
            var cosmetics = new Category(6L, "Cosmetics", "makeup.png", "Cosmetics", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(cosmetics);
            var petCare = new Category(7L, "Pet Care", "veterinary.png", "Pet Care", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(petCare);
            var books = new Category(8L, "Books", "books.png", "Books", CategoryStatus.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
            categoryRepository.save(books);
        }
    }
}
