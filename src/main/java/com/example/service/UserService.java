package com.example.service;

import com.example.dto.LoginDTO;
import com.example.dto.UserDTO;
import com.example.exception.JobPortalException;

public interface UserService {
	public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

	public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;
}
