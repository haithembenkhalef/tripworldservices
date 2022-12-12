package com.tripworld.exceptions.validators;

import com.tripworld.hotels.Hotel;
import com.tripworld.hotels.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j

public class RessourceNotFound implements ConstraintValidator<TargetNotFound, Hotel> {

    @Override
    public boolean isValid(Hotel s, ConstraintValidatorContext constraintValidatorContext) {
        log.info("data received from validator {}", s);
        return false;
    }
}
