package ru.akapich.invest_portfolio.validator.custom_validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.service.UserService;
import ru.akapich.invest_portfolio.validator.custom_interfaces.ExistingLogin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * JavaBean domain object that implements {@link ConstraintValidator}
 * to validate if login already exist in database
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class LoginValidator implements ConstraintValidator<ExistingLogin, String> {

	@Autowired
	private UserService userService;

	@Override
	public void initialize(ExistingLogin constraintAnnotation) {

	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
		return !userService.isNameExist(login);
	}
}