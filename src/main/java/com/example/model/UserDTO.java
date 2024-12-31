package com.example.model;

import com.example.entity.User;
import com.example.validation.ValidPhone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ValidPhone
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	@NotBlank(message = "{user.countryCode.required}")
	private String countryCode;
	@NotBlank(message = "{user.phoneNumber.required}")
	private String phoneNumber;
	@NotNull(message = "{user.accountType.required}")
	private AccountType accountType;
	private boolean otpVerified;
	
	public User toEntity() {
		return new User(this.id, this.countryCode, this.phoneNumber, this.accountType, this.otpVerified);
	}
}
