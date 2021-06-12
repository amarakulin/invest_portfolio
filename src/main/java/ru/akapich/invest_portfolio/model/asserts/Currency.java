package ru.akapich.invest_portfolio.model.asserts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JavaBean object that represents name of currency(USD, EUR etc)
 * of {@link ru.akapich.invest_portfolio.model.FinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_currency")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;
}
