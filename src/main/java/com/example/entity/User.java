package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.model.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
@CompoundIndex(name = "phoneNumber_accountType_idx", def = "{'phoneNumber': 1, 'accountType': 1}", unique = true)
public class User {
	@Id
	private Long id;
	private String countryCode;
	private String phoneNumber;
	private String name;
	private String emailId;
	private String password;
	private AccountType accountType;
	private boolean otpVerified;
}
