package com.example.api;

import com.example.exception.JobPortalException;
import com.example.model.request.LoginRequest;
import com.example.model.request.RegisterUserRequest;
import com.example.model.request.SendOtpRequest;
import com.example.model.request.VerifyOtpRequest;
import com.example.model.response.BaseResponse;
import com.example.model.response.LoginResponse;
import com.example.model.response.RegisterUserResponse;
import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserApi {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserRequest request) throws JobPortalException {
		var registerUserResponse = userService.registerUser(request);
		var sendOtpRequest = new SendOtpRequest(request.getCountryCode(), request.getPhoneNumber(), request.getAccountType());
		userService.sendOtp(sendOtpRequest);
		return new ResponseEntity<>(registerUserResponse, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) throws JobPortalException {
		var loginResponse = userService.loginUser(loginRequest);
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
	
	@PostMapping("/verifyOtp")
	public ResponseEntity<BaseResponse> verifyOtp(@RequestBody @Valid VerifyOtpRequest request) throws JobPortalException {
		return new ResponseEntity<>(userService.verifyOtp(request), HttpStatus.OK);
	}

	@PostMapping("/resendOtp")
	public ResponseEntity<BaseResponse> sendOtp(@RequestBody @Valid SendOtpRequest request) throws JobPortalException {
		return new ResponseEntity<>(userService.sendOtp(request), HttpStatus.OK);
	}
	
}
