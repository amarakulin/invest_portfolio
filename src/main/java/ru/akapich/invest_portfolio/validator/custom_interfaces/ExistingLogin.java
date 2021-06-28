package ru.akapich.invest_portfolio.validator.custom_interfaces;

import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.validator.custom_validators.LoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom interface to verify if login already exist in
 * {@link RegistrationForm}.
 *
 * @author Aleksandr Marakulin
 **/

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
@Documented
public @interface ExistingLogin {

	String message() default "{valid.existing.name}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
