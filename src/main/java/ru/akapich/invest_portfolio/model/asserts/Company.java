package ru.akapich.invest_portfolio.model.asserts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JavaBean object that represents info a company of
 * {@link ru.akapich.invest_portfolio.model.FinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_company")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String ticker;
}
