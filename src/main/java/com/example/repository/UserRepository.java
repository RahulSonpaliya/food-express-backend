package com.example.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
import com.example.model.AccountType;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
	public Optional<User> findByCountryCodeAndPhoneNumberAndAccountType(String countryCode, String phoneNumber, AccountType accountType);
}
