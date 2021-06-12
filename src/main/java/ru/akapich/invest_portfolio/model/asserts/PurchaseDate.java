package ru.akapich.invest_portfolio.model.asserts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import ru.akapich.invest_portfolio.model.InvestPortfolio;

import javax.persistence.*;
import java.util.Date;

/**
 *
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_purchase_date")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDate {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private InvestPortfolio investPortfolio;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;
}
