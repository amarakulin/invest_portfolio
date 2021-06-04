package ru.akapich.invest_portfolio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {

	private String login;
	private String email;
	private String password;

}
