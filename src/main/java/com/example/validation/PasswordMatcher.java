package com.example.validation;

import com.example.model.request.PasswordRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<MatchPassword, PasswordRequest> {

    @Override
    public boolean isValid(PasswordRequest request, ConstraintValidatorContext context) {
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            return false;
        }

        return request.getPassword().equals(request.getConfirmPassword());
    }
}
