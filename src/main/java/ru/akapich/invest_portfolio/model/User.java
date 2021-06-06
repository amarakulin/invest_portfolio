package ru.akapich.invest_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="t_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {


//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	//TODO do some with ID
	@Id
	@NonNull
	@Column
	private Long id;

	@Column
	private String login;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String role;

	@Column
	private boolean enable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
//TODO Add timestamp create
	//TODO Add access rules
}

