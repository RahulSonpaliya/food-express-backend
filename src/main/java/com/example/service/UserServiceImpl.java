package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.JobPortalException;
import com.example.model.LoginDTO;
import com.example.model.UserDTO;
import com.example.model.request.RegisterUserRequest;
import com.example.model.response.RegisterUserResponse;
import com.example.repository.UserRepository;
import com.example.utility.Utilities;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
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

}
