package ru.akapich.invest_portfolio.validator.custom_validators;

import org.springframework.beans.factory.annotation.Autowired;
import ru.akapich.invest_portfolio.service.UserService;
import ru.akapich.invest_portfolio.validator.custom_interfaces.ExistingEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * JavaBean domain object that implements {@link ConstraintValidator}
 * to validate if email already exist in database
 *
 * @author Aleksandr Marakulin
 **/

public class EmailValidator implements ConstraintValidator<ExistingEmail, String> {

	@Autowired
	private UserService userService;

	@Override
	public void initialize(ExistingEmail constraintAnnotation) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
		return !userService.isEmailExist(email);
	}
}
