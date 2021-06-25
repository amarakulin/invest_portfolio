package ru.akapich.invest_portfolio.model.user;

import lombok.*;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;

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
	@Size(min = 2, max = 10)//FIXME set max to 32
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String role;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//TODO why is Set???
//	private Set<InvestPortfolio> idInvestPortfolio;
//	private InvestPortfolio idInvestPortfolio;//TODO try with
// OR
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_invest_portfolio")
	private InvestPortfolio investPortfolio;

	@Column
	private boolean enable;
	//TODO Add timestamp create
	//TODO Add access rules
}

