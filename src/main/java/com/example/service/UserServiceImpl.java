package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.UserDTO;
import com.example.exception.JobPortalException;
import com.example.repository.UserRepository;
import com.example.utility.Utilities;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
		userDTO.setId(Utilities.getNextSequence("users"));
		var user = userRepository.save(userDTO.toEntity());
		return user.toDTO();
	}

}
