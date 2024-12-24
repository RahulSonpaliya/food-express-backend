package com.example.entity;

import com.example.dto.AccountType;

import lombok.Data;

@Data
public class User {
	private String id;
	private String name;
	private String email;
	private String password;
	private AccountType accountType;
}
