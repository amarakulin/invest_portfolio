package ru.akapich.invest_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.price_data.SetFinancialAssets;

import javax.persistence.*;

@Entity
@Table(name="t_statistic")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Statistic {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_set_financial_assets")
	private SetFinancialAssets idSetFinancialAssets;

	//foreign
	@ManyToOne
	@JoinColumn(name = "id_invest_portfolio", nullable = false)
	private InvestPortfolio investPortfolio;
}
