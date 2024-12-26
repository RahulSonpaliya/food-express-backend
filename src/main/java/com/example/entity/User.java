package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.dto.AccountType;

import lombok.Data;

@Data
@Document(collection = "users")
@CompoundIndex(name = "email_accountType_idx", def = "{'email': 1, 'accountType': 1}", unique = true)
public class User {
	@Id
	private String id;
	private String name;
	@Indexed(unique = true)
	private String email;
	private String password;
	private AccountType accountType;
}
