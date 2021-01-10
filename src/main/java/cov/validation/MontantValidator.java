package cov.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MontantValidator implements ConstraintValidator<MontantV, Double> {

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		return value >= 0;
	}

}
