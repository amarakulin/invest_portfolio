package ru.akapich.invest_portfolio.model.asset_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.asset_data.info.Company;
import ru.akapich.invest_portfolio.model.asset_data.info.Currency;
import ru.akapich.invest_portfolio.model.asset_data.info.Exchange;
import ru.akapich.invest_portfolio.model.asset_data.info.TypeAsset;

import javax.persistence.*;

@Entity
@Table(name="t_financial_asset")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialAsset {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_company")
	private Company idCompany;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_currency")
	private Currency idCurrency;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_type_asset")
	private TypeAsset idTypeAsset;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_exchange")
	private Exchange idExchange;

}
