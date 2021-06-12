package ru.akapich.invest_portfolio.model.price_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import ru.akapich.invest_portfolio.model.asset_data.FinancialAsset;
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

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_financial_asset")
	private FinancialAsset financialAsset;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_invest_portfolio")
	private InvestPortfolio investPortfolio;
}
