package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.JobPortalException;
import com.example.model.LoginDTO;
import com.example.model.UserDTO;
import com.example.model.request.RegisterUserRequest;
import com.example.model.response.RegisterUserResponse;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/users")
public class UserApi {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserRequest request) throws JobPortalException {
		return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO) throws JobPortalException {
		var userDto = userService.loginUser(loginDTO);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
}
