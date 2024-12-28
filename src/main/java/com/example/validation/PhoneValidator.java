package com.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dto.UserDTO;
import com.example.repository.AppRegionRepository;

public class PhoneValidator implements ConstraintValidator<ValidPhone, UserDTO> {

	@Autowired
	private AppRegionRepository appRegionRepository;
	
    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        if (userDTO.getCountryCode() == null || userDTO.getPhoneNumber() == null) {
            return false;
        }
        
        var appRegion = appRegionRepository.findByCalling(userDTO.getCountryCode());
        if(!appRegion.isPresent()) {
        	return false;
        }

        // Example logic: Concatenate country code and phone number and validate
        String fullPhoneNumber = userDTO.getCountryCode() + userDTO.getPhoneNumber();
        return fullPhoneNumber.matches("^\\+?[1-9][0-9]{5,15}$");
    }
}
