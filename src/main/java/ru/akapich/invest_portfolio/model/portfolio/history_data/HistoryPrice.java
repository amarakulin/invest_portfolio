package ru.akapich.invest_portfolio.model.portfolio.history_data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JavaBean domain object
 * That represent price changes on {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_history_price")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HistoryPrice {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String date;

	@Column
	private BigDecimal price;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_in_use_financial_asset", nullable = false)
	private FinancialAssetInUse idFinancialAssetInUse;
}
