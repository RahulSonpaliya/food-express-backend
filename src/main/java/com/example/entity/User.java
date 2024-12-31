package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.model.AccountType;
import com.example.model.UserDTO;

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
	private AccountType accountType;
	private boolean otpVerified;
	
	public UserDTO toDTO() {
		return new UserDTO(this.id, this.countryCode, this.phoneNumber, this.accountType, this.otpVerified);
	}
}
