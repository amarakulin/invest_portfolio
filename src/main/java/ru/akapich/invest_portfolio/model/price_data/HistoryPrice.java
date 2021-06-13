package ru.akapich.invest_portfolio.model.price_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.asset_data.FinancialAsset;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JavaBean domain object
 * That represent price changes on {@link FinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_history_price")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryPrice {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private BigDecimal price;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_financial_asset", nullable = false)
	private FinancialAsset financialAsset;
}
