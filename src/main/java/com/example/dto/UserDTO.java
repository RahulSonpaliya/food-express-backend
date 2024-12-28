package com.example.dto;

import com.example.entity.User;
import com.example.validation.ValidPhone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@NotBlank(message = "{user.password.required}")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,15}$", message = "{user.password.invalid}")
	private String password;
	@NotNull(message = "{user.accountType.required}")
	private AccountType accountType;
	
	public User toEntity() {
		return new User(this.id, this.countryCode, this.phoneNumber, this.password, this.accountType);
	}
}
