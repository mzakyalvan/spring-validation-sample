package com.tiket.tix.poc.validate.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author zakyalvan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Constraint(validatedBy = FloodRejectionValidator.class)
@Documented
@Inherited
public @interface RejectFlood {
    String message() default "{com.tiket.tix.poc.validate.validate.RejectFlood.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
