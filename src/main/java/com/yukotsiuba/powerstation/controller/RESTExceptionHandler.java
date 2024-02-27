package com.yukotsiuba.powerstation.controller;

import com.yukotsiuba.powerstation.exception.APIException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationExceptionException(ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();
        ex.getConstraintViolations().forEach(
                violation -> details.add(violation.getMessage())
        );

        APIException apiException = new APIException("Constraint Violations.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        APIException apiException = new APIException("Failed to read request.", HttpStatus.BAD_REQUEST,
                LocalDateTime.now(), details);
        return new ResponseEntity<>(apiException, apiException.getHttpStatus());
    }
}
