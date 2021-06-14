package ru.akapich.invest_portfolio.model.portfolio.asset_data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.Currency;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.Exchange;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.TypeAsset;

import javax.persistence.*;
import java.util.Objects;

/**
 * JavaBean domain object that represents all assets
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_all_financial_asset")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AllFinancialAsset that = (AllFinancialAsset) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(ticker, that.ticker) &&
				Objects.equals(name, that.name) &&
				Objects.equals(idCurrency, that.idCurrency) &&
				Objects.equals(idTypeAsset, that.idTypeAsset) &&
				Objects.equals(idExchange, that.idExchange);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ticker, name, idCurrency, idTypeAsset, idExchange);
	}
}
