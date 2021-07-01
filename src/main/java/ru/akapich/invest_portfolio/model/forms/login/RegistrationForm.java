package ru.akapich.invest_portfolio.model.forms.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class RegistrationForm {

	@Size(min = 2, max = 32, message = "{valid.size.name}")
	@ExistingLogin
	private String name;

	@NotBlank(message = "{valid.empty.email}")
	@Email(message = "{valid.email}")
	@ExistingEmail
	private String email;

	@Size(min = 5, max = 32, message = "{valid.size.password}")
	private String password;

	@Size(min = 5, max = 32, message = "{valid.size.password}")
	private String rePassword;

	@Override
	public Object clone() throws CloneNotSupportedException {
		RegistrationForm registrationForm = null;
		try{
			registrationForm = (RegistrationForm) super.clone();
		}
		catch (CloneNotSupportedException e){
			registrationForm = RegistrationForm.builder()
					.name(this.name)
					.email(this.email)
					.password(this.password)
					.rePassword(this.rePassword)
					.build();
		}
		return registrationForm;
	}
}