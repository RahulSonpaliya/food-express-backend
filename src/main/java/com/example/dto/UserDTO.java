package com.example.dto;

import com.example.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Long id;
	@NotBlank(message = "{user.name.required}")
	private String name;
	@NotBlank(message = "{user.email.required}")
	@Email(message = "{user.email.invalid}")
	private String email;
	@NotBlank(message = "{user.password.required}")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,15}$", message = "{user.password.invalid}")
	private String password;
	@NotNull(message = "{user.accountType.required}")
	private AccountType accountType;
	
	public User toEntity() {
		return new User(this.id, this.name, this.email, this.password, this.accountType);
	}
}