package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	@NotBlank(message = "{user.email.required}")
	private String email;
	@NotBlank(message = "{user.password.required}")
	private String password;
	@NotNull(message = "{user.accountType.required}")
	private AccountType accountType;
}
