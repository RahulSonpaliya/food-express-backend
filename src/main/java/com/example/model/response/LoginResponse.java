package com.example.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse extends BaseResponse {

	private boolean otpVerified;
	private Long userId;
	private LoggedInUserDTO user;

	public LoginResponse(String message, boolean success) {
		super(message, success);
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class LoggedInUserDTO {
		private Long id;
		private String countryCode;
		private String phoneNumber;
		private String name;
		private String emailId;
	}
}
