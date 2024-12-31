package com.example.service;

import com.example.exception.JobPortalException;
import com.example.model.LoginDTO;
import com.example.model.UserDTO;
import com.example.model.request.RegisterUserRequest;
import com.example.model.response.RegisterUserResponse;

public interface UserService {
	public RegisterUserResponse registerUser(RegisterUserRequest request) throws JobPortalException;

	public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;
}
