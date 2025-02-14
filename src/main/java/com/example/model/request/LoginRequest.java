package com.example.model.request;

import com.example.model.AccountType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	@NotBlank(message = "{user.countryCode.required}")
	private String countryCode;
	@NotBlank(message = "{user.phoneNumber.required}")
	private String phoneNumber;
	@NotBlank(message = "{user.password.required}")
	private String password;
	@NotNull(message = "{user.accountType.required}")
	private AccountType accountType;
}
