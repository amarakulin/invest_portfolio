package ru.akapich.invest_portfolio.model.Forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationFrom {

	private String login;
	private String email;
	private String password;
	private String confirmPassword;
}
