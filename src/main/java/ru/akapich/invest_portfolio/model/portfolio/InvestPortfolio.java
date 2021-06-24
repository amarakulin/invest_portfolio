package ru.akapich.invest_portfolio.model.portfolio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.AddingNewListFinancialAsset;
import ru.akapich.invest_portfolio.model.user.User;
import javax.persistence.*;

/**
 * JavaBean object that represents a number of invest portfolio
 * A {@link User} have a pointer of the number.
 *
 *
 * @author Aleksandr Marakulin
 **/

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

//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "id_set_financial_assets")
//	private SetFinancialAssets idSetFinancialAssets;

//	@OneToMany
//	@JoinColumn(name = "id_statistic")
//	private Set<Statistic> idStatistic;

}
