package com.example.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterUserResponse extends BaseResponse {

	private boolean otpVerified;
	private Long userId;
	
	public RegisterUserResponse(String message, boolean success) {
		super(message, success);
	}
}
