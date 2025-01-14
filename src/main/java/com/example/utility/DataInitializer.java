package com.example.utility;

import com.example.entity.AppRegion;
import com.example.entity.Category;
import com.example.entity.Market;
import com.example.exception.JobPortalException;
import com.example.model.CategoryStatus;
import com.example.model.MarketStatus;
import com.example.repository.AppRegionRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.MarketRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private AppRegionRepository appRegionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MarketRepository marketRepository;

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
        if (marketRepository.count() == 0) {
            List<Category> categories = categoryRepository.findAll();
            double baseLatitude = 22.7377401;
            double baseLongitude = 75.8173344;
            var mId = 1L;

            for (Category category : categories) {
                for (int i = 1; i <= 4; i++, mId++) { // Add 4 markets per category
                    double latitudeOffset = (Math.random() - 0.5) / 100; // Random offset near the base location
                    double longitudeOffset = (Math.random() - 0.5) / 100;
                    var market = new Market(
                            mId, // Auto-generated ID
                            category.getId(),
                            category.getName().toLowerCase() + "-market-" + i,
                            category.getName().toLowerCase() + "-market-" + i + ".png",
                            "Address for " + category.getName() + " Market " + i,
                            MarketStatus.ACTIVE,
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)), // Opening time
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 0)),
                            Math.round((3.5 + Math.random() * 1.5) * 10) / 10.0, // Random rating between 3.5 and 5.0
                            baseLatitude + latitudeOffset,
                            baseLongitude + longitudeOffset
                    );
                    marketRepository.save(market);
                }
            }
        }
    }
}
