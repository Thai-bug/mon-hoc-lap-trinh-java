package com.validator.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
@Scope("request")
public class PhoneConstraint implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String phoneRegex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        boolean valid = Pattern.compile(phoneRegex).matcher(value).matches();
        return valid;
    }
}
