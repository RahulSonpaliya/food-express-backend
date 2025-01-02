package com.example.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginResponse extends BaseResponse {

	private boolean otpVerified;
	private Long userId;

	public LoginResponse(String message, boolean success) {
		super(message, success);
	}
}
