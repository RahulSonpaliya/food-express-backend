package com.example.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.AppRegion;

@Repository
public interface AppRegionRepository extends MongoRepository<AppRegion, String> {
	public Optional<AppRegion> findByCalling(String calling);
}
