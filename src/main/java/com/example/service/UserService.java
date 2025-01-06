package com.example.service;

import com.example.exception.JobPortalException;
import com.example.model.request.*;
import com.example.model.response.BaseResponse;
import com.example.model.response.LoginResponse;
import com.example.model.response.RegisterUserResponse;

public interface UserService {
	public RegisterUserResponse registerUser(RegisterUserRequest request) throws JobPortalException;

	public LoginResponse loginUser(LoginRequest loginRequest) throws JobPortalException;
	
	public BaseResponse sendOtp(SendOtpRequest sendOtpRequest) throws JobPortalException;
	
	public BaseResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) throws JobPortalException;

	BaseResponse resetPassword(ResetPasswordRequest request) throws JobPortalException;
}
