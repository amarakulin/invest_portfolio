package ru.akapich.invest_portfolio.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * JavaBean domain object that represents a User.
 *
 * @author Aleksandr Marakulin
**/

@Entity
@Table(name="t_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Size(min = 2, max = 10)
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String role;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//(mappedBy = "t_user")
	private Set<InvestPortfolio> idInvestPortfolio;

	@Column
	private boolean enable;
	//TODO Add timestamp create
	//TODO Add access rules
}

