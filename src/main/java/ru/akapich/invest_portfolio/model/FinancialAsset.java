package ru.akapich.invest_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.asserts.*;

import javax.persistence.*;
import java.util.List;

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

	@OneToOne
	private Company idCompany;

	@OneToOne
	private Currency idCurrency;

	@OneToOne
	private TypeAsset idTypeAsset;

	@OneToOne
	private Exchange idExchange;

	@OneToOne
	private HistoryPrice historyPrice;//TODO may be different structure of the field

	@OneToMany(mappedBy="t_financial_asset")
	private List<PurchaseDate> idPurchaseDate;
}
