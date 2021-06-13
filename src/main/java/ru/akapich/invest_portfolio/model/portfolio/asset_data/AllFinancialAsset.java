package ru.akapich.invest_portfolio.model.portfolio.asset_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.Currency;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.Exchange;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.TypeAsset;

import javax.persistence.*;

/**
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_all_financial_asset")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllFinancialAsset {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String ticker;

	@Column
	private String name;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_currency")
	private Currency idCurrency;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_type_asset")
	private TypeAsset idTypeAsset;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_exchange")
	private Exchange idExchange;
}
