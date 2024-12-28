package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exception.JobPortalException;
import com.example.repository.UserRepository;
import com.example.utility.Utilities;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
		var user1 = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(userDTO.getCountryCode(), userDTO.getPhoneNumber(), userDTO.getAccountType());
		if(user1.isPresent()) {
			throw new JobPortalException("USER_FOUND");
		}
		userDTO.setId(Utilities.getNextSequence("users"));
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		var user = userRepository.save(userDTO.toEntity());
		return user.toDTO();
	}

	@Override
	public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
		var user = userRepository.findByCountryCodeAndPhoneNumberAndAccountType(loginDTO.getCountryCode(), loginDTO.getPhoneNumber(), loginDTO.getAccountType()).orElseThrow(() -> new JobPortalException("INVALID_CREDENTIALS"));
		if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
			throw new JobPortalException("INVALID_CREDENTIALS");
		}
		return user.toDTO();
	}

}
