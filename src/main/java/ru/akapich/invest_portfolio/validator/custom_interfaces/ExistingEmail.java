package ru.akapich.invest_portfolio.validator.custom_interfaces;

import ru.akapich.invest_portfolio.validator.custom_validators.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Custom interface to verify if email already exist in
 * {@link ru.akapich.invest_portfolio.model.Forms.RegistrationFrom}.
 *
 * @author Aleksandr Marakulin
 **/

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ExistingEmail {
	String message() default "valid.existing.email";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
