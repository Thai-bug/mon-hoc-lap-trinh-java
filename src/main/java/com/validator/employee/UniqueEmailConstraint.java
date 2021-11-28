package com.validator.employee;

import com.pojos.Employee;
import com.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Scope("request")
public class UniqueEmailConstraint implements ConstraintValidator<UniqueEmail, String>{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Employee employee = employeeRepository.getEmployeeByEmail(email);
        if(employee == null)
            return true;
        else
            return false;
    }
}