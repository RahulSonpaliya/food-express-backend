package com.example.entity;

import com.example.model.MarketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "markets")
public class Market {
    @Id
    private Long id;
    private Long categoryId;
    private String name;
    private String image;
    private String address;
    private MarketStatus status;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private double rating;
    private double latitude;
    private double longitude;
}
