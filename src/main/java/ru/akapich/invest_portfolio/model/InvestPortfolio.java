package ru.akapich.invest_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.price_data.SetFinancialAssets;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_invest_portfolio")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestPortfolio {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_set_financial_assets")
	private SetFinancialAssets idSetFinancialAssets;

	@OneToMany//(mappedBy = "t_invest_portfolio")
	private Set<Statistic> idStatistic;


}
