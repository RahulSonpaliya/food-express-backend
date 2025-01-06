package com.example.service;

import com.example.entity.OTP;
import com.example.entity.User;
import com.example.exception.JobPortalException;
import com.example.model.request.*;
import com.example.model.response.BaseResponse;
import com.example.model.response.LoginResponse;
import com.example.model.response.RegisterUserResponse;
import com.example.repository.OTPRepository;
import com.example.repository.UserRepository;
import com.example.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private OTPRepository otpRepository;
	
	@Override
	public RegisterUserResponse registerUser(RegisterUserRequest request) throws JobPortalException {
		var user1 = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(request.getCountryCode(), request.getPhoneNumber(), request.getAccountType());
		if(user1.isPresent()) {
			throw new JobPortalException("USER_FOUND");
		}
		var userEntity = new User(Utilities.getNextSequence("users"), request.getCountryCode(), request.getPhoneNumber(), request.getName(), request.getEmailId(), passwordEncoder.encode(request.getPassword()), request.getAccountType(), false);
		var savedUser = userRepository.save(userEntity);
		var response = new RegisterUserResponse("User registered successfully", true);
		response.setOtpVerified(savedUser.isOtpVerified());
		response.setUserId(savedUser.getId());
		return response;
	}

	@Override
	public LoginResponse loginUser(LoginRequest loginRequest) throws JobPortalException {
		var user = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(loginRequest.getCountryCode(), loginRequest.getPhoneNumber(), loginRequest.getAccountType()).orElseThrow(() -> new JobPortalException("INVALID_CREDENTIALS"));
		if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			throw new JobPortalException("INVALID_CREDENTIALS");
		}
		var responseMsg = "Logged in successfully.";
		if(!user.isOtpVerified()) {
			responseMsg = "Please verify your phone number to continue.";
			var phoneNum = user.getCountryCode() + user.getPhoneNumber();
			saveOTPEntity(phoneNum);
		}
		var loginResponse = new LoginResponse(responseMsg, true);
		loginResponse.setOtpVerified(user.isOtpVerified());
		loginResponse.setUserId(user.getId());
		if(user.isOtpVerified()) {
			var loggedInUserDTO = new LoginResponse.LoggedInUserDTO(
					user.getId(),
					user.getCountryCode(),
					user.getPhoneNumber(),
					user.getName(),
					user.getEmailId()
			);
			loginResponse.setUser(loggedInUserDTO);
		}
		return loginResponse;
	}

	@Override
	public BaseResponse sendOtp(SendOtpRequest sendOtpRequest) throws JobPortalException {
		var user = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(sendOtpRequest.getCountryCode(), sendOtpRequest.getPhoneNumber(), sendOtpRequest.getAccountType()).orElseThrow(() -> new JobPortalException("INVALID_CREDENTIALS"));
		var phoneNum = sendOtpRequest.getCountryCode() + sendOtpRequest.getPhoneNumber();
		saveOTPEntity(phoneNum);
		return new BaseResponse("OTP sent successfully", true);
	}

	private void saveOTPEntity(String phoneNum) {
		String otpVal = Utilities.generateOTP();
		var otp = new OTP(phoneNum, otpVal, LocalDateTime.now());
		otpRepository.save(otp);
		// TODO implement otp sending functionality using sms api i.e. Twillio
	}

	@Override
	public BaseResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) throws JobPortalException {
		var user = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(verifyOtpRequest.getCountryCode(), verifyOtpRequest.getPhoneNumber(), verifyOtpRequest.getAccountType()).orElseThrow(() -> new JobPortalException("INVALID_CREDENTIALS"));
		var phoneNum = verifyOtpRequest.getCountryCode() + verifyOtpRequest.getPhoneNumber();
		var otpEntity = otpRepository.findById(phoneNum).orElseThrow(() -> new JobPortalException("OTP_NOT_FOUND"));
		if(!otpEntity.getOtpCode().equals(verifyOtpRequest.getOtp())) {
			throw new JobPortalException("INCORRECT_OTP");
		}
		otpRepository.delete(otpEntity);
		user.setOtpVerified(true);
		userRepository.save(user);
		return new BaseResponse("OTP verified successfully", true);
	}

	@Override
	public BaseResponse resetPassword(ResetPasswordRequest request) throws JobPortalException {
		var user = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(request.getCountryCode(), request.getPhoneNumber(), request.getAccountType()).orElseThrow(() -> new JobPortalException("INVALID_CREDENTIALS"));
		if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new JobPortalException("SAME_PASSWORD");
		}
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepository.save(user);
		return new BaseResponse("Password reset successfully", true);
	}

}
