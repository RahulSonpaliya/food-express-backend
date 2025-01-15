package com.example.entity;

import com.example.model.MarketStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
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
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    public Market(Long id, Long categoryId, String name, String image, String address, MarketStatus status,
                  LocalDateTime openTime, LocalDateTime closeTime, double rating,
                  double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.status = status;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = new GeoJsonPoint(longitude, latitude);
        this.categoryId = categoryId;
    }
}
