package com.validator.employee;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueEmailConstraint.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
