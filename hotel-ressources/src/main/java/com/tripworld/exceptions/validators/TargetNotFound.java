package com.tripworld.exceptions.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RessourceNotFound.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetNotFound {

    String message() default "{com.tripworld.exceptions.validators}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
