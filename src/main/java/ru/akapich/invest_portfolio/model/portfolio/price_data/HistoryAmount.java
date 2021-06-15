package ru.akapich.invest_portfolio.model.portfolio.price_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JavaBean domain object
 * That represent amount of assert changes on {@link OwnedFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_history_amount")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryAmount {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private BigDecimal amount;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_owned_financial_asset", nullable = false)
	private OwnedFinancialAsset ownedFinancialAsset;
}
