package com.validator.employee;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
@Scope("request")
public class CompanyEmailConstraint implements ConstraintValidator<CompanyEmail, String>{

    @Override
    public void initialize(CompanyEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Pattern emailRegex = Pattern.compile("^\\w+([\\.-]?\\w+)*@akay.com+$");
        boolean valid = emailRegex.matcher(email).matches();
        return valid;
    }
}
