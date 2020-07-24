package com.mindtree.order.constraintvalidators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = OrderConstraintValidator.class)
@Target({ ElementType.TYPE, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrder {

	String message() default "Invalid Order";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
