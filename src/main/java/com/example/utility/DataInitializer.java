package com.example.utility;

import com.example.entity.AppRegion;
import com.example.entity.Category;
import com.example.entity.Market;
import com.example.entity.Product;
import com.example.exception.JobPortalException;
import com.example.model.CategoryStatus;
import com.example.model.MarketStatus;
import com.example.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private AppRegionRepository appRegionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

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

        if (productRepository.count() == 0) {
            List<Market> markets = marketRepository.findAll();
            var pId = 0L;
            for (Market market : markets) {
                // Create a new ArrayList to hold Product objects
                List<Product> menu = new ArrayList<>();

                // Add the restaurant products to the list
                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Classic Cheeseburger",
                        null,
                        "A juicy, flame-grilled beef patty with melted cheddar cheese, crisp lettuce, fresh tomato, and onion on a toasted brioche bun.",
                        "1 burger",
                        12.99
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Margherita Pizza",
                        null,
                        "Traditional Neapolitan pizza with San Marzano tomato sauce, fresh mozzarella cheese, and basil, drizzled with olive oil.",
                        "1 pizza (10-inch)",
                        15.50
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Fettuccine Alfredo",
                        null,
                        "Creamy fettuccine pasta tossed in a rich, homemade Parmesan and cream sauce.",
                        "1 serving",
                        16.75
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Caesar Salad",
                        null,
                        "Crisp romaine lettuce, Parmesan cheese, and croutons with a creamy Caesar dressing. Add grilled chicken for $4.00.",
                        "1 salad",
                        10.25
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Chicken Wings",
                        null,
                        "Eight crispy chicken wings served with your choice of barbecue, buffalo, or sweet chili sauce.",
                        "8 wings",
                        14.00
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "French Fries",
                        null,
                        "A generous portion of golden, crispy potato fries, served with a side of ketchup.",
                        "1 portion",
                        5.50
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Grilled Salmon",
                        null,
                        "Perfectly grilled salmon fillet, seasoned with lemon and herbs, served with a side of roasted asparagus.",
                        "1 fillet",
                        24.95
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Chocolate Lava Cake",
                        null,
                        "Warm, rich chocolate cake with a molten chocolate center, topped with a scoop of vanilla ice cream.",
                        "1 cake",
                        9.50
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Coffee",
                        null,
                        "Freshly brewed coffee, available with milk, cream, and sugar.",
                        "1 cup",
                        3.00
                ));

                menu.add(new Product(
                        ++pId,
                        market.getId(),
                        "Iced Tea",
                        null,
                        "Sweet or unsweetened iced tea with a lemon wedge.",
                        "1 glass",
                        3.50
                ));

                productRepository.saveAll(menu);
            }
        }
    }
}
