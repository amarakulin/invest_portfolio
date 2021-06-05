package ru.akapich.invest_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {

	@Id
	Long id;

	private String login;
	private String email;
	private String password;
}
