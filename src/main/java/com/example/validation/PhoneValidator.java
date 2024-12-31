package com.example.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.request.PhoneNumberRequest;
import com.example.repository.AppRegionRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<ValidPhone, PhoneNumberRequest> {

	@Autowired
	private AppRegionRepository appRegionRepository;
	
    @Override
    public boolean isValid(PhoneNumberRequest request, ConstraintValidatorContext context) {
        if (request.getCountryCode() == null || request.getPhoneNumber() == null) {
            return false;
        }
        
        var appRegion = appRegionRepository.findByCalling(request.getCountryCode());
        if(!appRegion.isPresent()) {
        	return false;
        }

        // Example logic: Concatenate country code and phone number and validate
        String fullPhoneNumber = request.getCountryCode() + request.getPhoneNumber();
        return fullPhoneNumber.matches("^\\+?[1-9][0-9]{5,15}$");
    }
}
