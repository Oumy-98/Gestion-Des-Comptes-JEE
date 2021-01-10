package cov.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint (validatedBy= MontantValidator.class)
public @interface MontantV {

	
	String message() default "ce montant ne doit pas etre négatif";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
