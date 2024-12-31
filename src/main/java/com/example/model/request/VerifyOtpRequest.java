package com.example.model.request;

import com.example.model.AccountType;
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
public class VerifyOtpRequest implements PhoneNumberRequest {
	@NotBlank(message = "{user.countryCode.required}")
	private String countryCode;
	@NotBlank(message = "{user.phoneNumber.required}")
	private String phoneNumber;
	@NotNull(message = "{user.accountType.required}")
	private AccountType accountType;
	@NotBlank(message = "{user.otp.required}")
	private String otp;
}
