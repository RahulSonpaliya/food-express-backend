package com.example.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "otp")
public class OTP {
	@Id
	private String phoneNumber;
	private String otpCode;
	private LocalDateTime creationTime;
}
