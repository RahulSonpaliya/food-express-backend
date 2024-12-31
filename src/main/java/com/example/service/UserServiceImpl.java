package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.OTP;
import com.example.exception.JobPortalException;
import com.example.model.LoginDTO;
import com.example.model.UserDTO;
import com.example.model.request.RegisterUserRequest;
import com.example.model.request.SendOtpRequest;
import com.example.model.request.VerifyOtpRequest;
import com.example.model.response.BaseResponse;
import com.example.model.response.RegisterUserResponse;
import com.example.repository.OTPRepository;
import com.example.repository.UserRepository;
import com.example.utility.Utilities;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OTPRepository otpRepository;
	
	@Override
	public RegisterUserResponse registerUser(RegisterUserRequest request) throws JobPortalException {
		var user1 = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(request.getCountryCode(), request.getPhoneNumber(), request.getAccountType());
		if(user1.isPresent()) {
			throw new JobPortalException("USER_FOUND");
		}
		request.setId(Utilities.getNextSequence("users"));
		var savedUser = userRepository.save(request.toUserEntity());
		var response = new RegisterUserResponse("User registered successfully", true);
		response.setOtpVerified(savedUser.isOtpVerified());
		response.setUserId(savedUser.getId());
		return response;
	}

	@Override
	public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
		var user = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(loginDTO.getCountryCode(), loginDTO.getPhoneNumber(), loginDTO.getAccountType()).orElseThrow(() -> new JobPortalException("INVALID_CREDENTIALS"));
		return user.toDTO();
	}

	@Override
	public BaseResponse sendOtp(SendOtpRequest sendOtpRequest) throws JobPortalException {
		var user = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(sendOtpRequest.getCountryCode(), sendOtpRequest.getPhoneNumber(), sendOtpRequest.getAccountType()).orElseThrow(() -> new JobPortalException("INVALID_CREDENTIALS"));
		String otpVal = Utilities.generateOTP();
		var phoneNum = sendOtpRequest.getCountryCode() + sendOtpRequest.getPhoneNumber();
		var otp = new OTP(phoneNum, otpVal, LocalDateTime.now());
		otpRepository.save(otp);
		// TODO implement otp sending functionality using sms api i.e. Twillio
		return new BaseResponse("OTP sent successfully", true);
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

}
