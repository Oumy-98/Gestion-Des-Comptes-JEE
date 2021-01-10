package cov.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateV, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean resul;
		try {
			
			String[] elements = value.split("-");
			int mm = Integer.parseInt(elements[1]);
			int dd = Integer.parseInt(elements[2]);

			resul = mm > 0 && mm < 13 && dd > 0 && dd < 32;
		} catch (Exception e) {
			return false;
		}

		return value.matches("[0-9][0-9][0-9][0-9]+-+[0-9]+-+[0-9][0-9]") && resul;
	}

}
