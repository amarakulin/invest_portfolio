package ru.akapich.invest_portfolio.model.forms.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.validator.login.custom_interfaces.ExistingEmail;
import ru.akapich.invest_portfolio.validator.login.custom_interfaces.ExistingLogin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Object that get form client to register new user.
 *
 * @author Aleksandr Marakulin
 **/


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RegistrationForm {

	@NotBlank(message = "{valid.empty.name}")
	@Size(min = 2, max = 32, message = "{valid.size.name}")
	@ExistingLogin
	private String name;

	@NotBlank(message = "{valid.empty.email}")
	@Email(message = "{valid.email}")
	@ExistingEmail
	private String email;

	@NotBlank(message = "{valid.empty.password}")
	@Size(min = 5, max = 32, message = "{valid.size.password}")
	private String password;

	@NotBlank(message = "{valid.empty.password}")
	@Size(min = 5, max = 32, message = "{valid.size.password}")
	private String rePassword;
}