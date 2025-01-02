package com.example.validation;

import com.example.model.request.RegisterUserRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<MatchPassword, RegisterUserRequest> {
	
    @Override
    public boolean isValid(RegisterUserRequest request, ConstraintValidatorContext context) {
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            return false;
        }

        return request.getPassword().equals(request.getConfirmPassword());
    }
}
