package com.example.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {
	private final String message;
	private final boolean success;
}
