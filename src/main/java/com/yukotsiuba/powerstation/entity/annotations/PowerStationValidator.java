package com.yukotsiuba.powerstation.entity.annotations;

import com.yukotsiuba.powerstation.configuration.validators.PowerStationRequestValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PowerStationRequestValidator.class)
public @interface PowerStationValidator {
    String message() default "Invalid PowerStationRequest";

    String[] conditionallyMandatoryFields() default {};
}
