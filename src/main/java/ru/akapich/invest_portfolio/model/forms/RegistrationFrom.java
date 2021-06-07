package ru.akapich.invest_portfolio.model.Forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.validator.custom_interfaces.ExistingEmail;
import ru.akapich.invest_portfolio.validator.custom_interfaces.ExistingLogin;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFrom {

	//TODO if size invalid and existingLogin occures an error, because two messages
	@NotBlank(message = "valid.empty.login")
	@Size(min = 5, max = 32, message = "valid.size.login")
	@ExistingLogin
	private String login;

	@NotBlank(message = "valid.empty.email")
	@Email(message = "valid.email")
	@ExistingEmail
	private String email;

	@NotBlank(message = "valid.empty.password")
	@Size(min = 5, max = 32, message = "valid.size.password")
	private String password;

	@NotBlank(message = "valid.empty.password")
	@Size(min = 5, max = 32, message = "valid.size.password")
	private String confirmPassword;
}