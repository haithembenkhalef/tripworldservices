package com.tripworld.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleException(ConstraintViolationException e) {
        final List<Object> errors = new ArrayList<>();
        e.getConstraintViolations().stream().forEach(fieldError -> {
            Map<String, Object> error = new HashMap<>();
            error.put("path", String.valueOf(fieldError.getPropertyPath()));
            error.put("messgae", fieldError.getMessage());
            errors.add(error);
        });
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, Object> body = new HashMap<>();
        body.put("error", errors);
        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<?> handleNoRecordFoundException(NoRecordFoundException ex)
    {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }



}