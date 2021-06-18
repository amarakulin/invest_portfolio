package ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.Statistic;

import javax.persistence.*;
import java.util.List;

/**
 * JavaBean domain object that represents asset for a special {@link InvestPortfolio}
 *
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_owned_financial_asset")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OwnedFinancialAsset {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_invest_portfolio", nullable = false)
	private InvestPortfolio investPortfolio;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_in_use_financial_asset", nullable = false)
	private FinancialAssetInUse FinancialAssetInUse;

	@ManyToMany(mappedBy = "idOwnedFinancialAssets")
	private List<Statistic> idStatistics;

}
