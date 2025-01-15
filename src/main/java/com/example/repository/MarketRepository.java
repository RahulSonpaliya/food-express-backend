package com.example.repository;

import com.example.entity.Market;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends MongoRepository<Market, Long> {
    @Query("{ 'location': { $near: { $geometry: { type: 'Point', coordinates: [?1, ?0] }, $maxDistance: 500 } } }")
    List<Market> findMarketsWithinRadius(double latitude, double longitude);
}
