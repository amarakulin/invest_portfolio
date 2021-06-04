package ru.akapich.invest_portfolio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Id
	private Long id;
	private String login;
	private String email;
	private String password;

}
