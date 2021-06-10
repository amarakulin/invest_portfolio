package ru.akapich.invest_portfolio.model.forms;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.validator.custom_interfaces.ExistingEmail;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

	@ExistingEmail
	private String email;
	private String password;
}
