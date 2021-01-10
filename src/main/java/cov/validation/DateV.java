package cov.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface DateV {
	
	String message() default "la date doit etre de la forme yyyy-mm-dd";

	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
