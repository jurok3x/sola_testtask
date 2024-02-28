package com.yukotsiuba.powerstation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.List;

@Data
@AllArgsConstructor
public class PowerStationRequestException extends Exception {
    List<ObjectError> errors;
}
