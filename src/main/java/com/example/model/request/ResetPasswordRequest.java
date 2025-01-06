package com.example.model.request;

import com.example.model.AccountType;
import com.example.validation.MatchPassword;
import com.example.validation.ValidPhone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MatchPassword
@ValidPhone
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest implements PhoneNumberRequest, PasswordRequest {
	@NotBlank(message = "{user.countryCode.required}")
	private String countryCode;
	@NotBlank(message = "{user.phoneNumber.required}")
	private String phoneNumber;
	@NotBlank(message = "{user.password.required}")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,15}$", message = "{user.password.invalid}")
	private String password;
	@NotBlank(message = "{user.confirmPassword.required}")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,15}$", message = "{user.confirmPassword.invalid}")
	private String confirmPassword;
	@NotNull(message = "{user.accountType.required}")
	private AccountType accountType;
}