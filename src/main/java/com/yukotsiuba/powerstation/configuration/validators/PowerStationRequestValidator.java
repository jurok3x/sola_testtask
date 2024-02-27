package com.yukotsiuba.powerstation.configuration.validators;

import com.yukotsiuba.powerstation.dto.PowerStationRequestDTO;
import com.yukotsiuba.powerstation.entity.annotations.PowerStationValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.yukotsiuba.powerstation.utils.ValidationUtils.capitalize;
import static com.yukotsiuba.powerstation.utils.ValidationUtils.isEmpty;

public class PowerStationRequestValidator implements ConstraintValidator<PowerStationValidator, PowerStationRequestDTO> {

    private String[] conditionallyMandatoryFields;

    @Override
    public void initialize(PowerStationValidator constraintAnnotation) {
        conditionallyMandatoryFields = constraintAnnotation.conditionallyMandatoryFields();
    }

    @Override
    public boolean isValid(PowerStationRequestDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isPublic = Boolean.TRUE.equals(value.getIsPublic());

        if (isPublic) {
            List<String> errorFields = new ArrayList<>();
            for (String field : conditionallyMandatoryFields) {
                if (isEmpty(getFieldValue(value, field))) {
                    errorFields.add(field);
                }
            }
            if(!errorFields.isEmpty()) {
                addConstraintViolations(context, errorFields);
                return false;
            }
        }
        return true;
    }

    private Object getFieldValue(PowerStationRequestDTO station, String fieldName) {
        try {
            Field field = PowerStationRequestDTO.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(station);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error accessing field: " + fieldName, e);
        }
    }

    private void addConstraintViolations(ConstraintValidatorContext context, List<String> errorFields) {
        for(String field: errorFields) {
            String message = String.format("%s is mandatory for public stations.", capitalize(field));
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(field)
                    .addConstraintViolation();
        }
    }
}
