package com.yukotsiuba.powerstation.configuration.validators;

import com.yukotsiuba.powerstation.entity.PowerStation;
import com.yukotsiuba.powerstation.entity.annotations.PowerStationValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

import static com.yukotsiuba.powerstation.utils.ValidationUtils.capitalize;
import static com.yukotsiuba.powerstation.utils.ValidationUtils.isEmpty;

public class PowerStationRequestValidator implements ConstraintValidator<PowerStationValidator, PowerStation> {

    private String[] conditionallyMandatoryFields;

    @Override
    public void initialize(PowerStationValidator constraintAnnotation) {
        conditionallyMandatoryFields = constraintAnnotation.conditionallyMandatoryFields();
    }

    @Override
    public boolean isValid(PowerStation value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isPublic = Boolean.TRUE.equals(value.getIsPublic());

        if (isPublic) {
            for (String field : conditionallyMandatoryFields) {
                if (isEmpty(getFieldValue(value, field))) {
                    addConstraintViolation(context, capitalize(field) + " is mandatory when 'isPublic' is true.", field);
                    return false;
                }
            }
        }
        return true;
    }

    private Object getFieldValue(PowerStation station, String fieldName) {
        try {
            Field field = PowerStation.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(station);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error accessing field: " + fieldName, e);
        }
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message, String fieldName) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation();
    }
}
