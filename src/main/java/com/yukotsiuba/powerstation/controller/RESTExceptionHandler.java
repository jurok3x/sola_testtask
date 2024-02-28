package com.yukotsiuba.powerstation.controller;

import com.yukotsiuba.powerstation.exception.APIException;
import com.yukotsiuba.powerstation.exception.PowerStationRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RESTExceptionHandler {

    @ExceptionHandler(PowerStationRequestException.class)
    public ResponseEntity<?> handleValidationExceptions(PowerStationRequestException ex) {
        List<String> errors = ex.getErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        APIException apiException = new APIException("Validation Errors.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), errors);

        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
