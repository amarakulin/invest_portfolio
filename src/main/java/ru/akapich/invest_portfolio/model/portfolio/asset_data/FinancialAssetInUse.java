package ru.akapich.invest_portfolio.model.portfolio.asset_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JavaBean domain object that represents a assets in use.
 * Collected form {@link AllFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/
@Entity
@Table(name="t_financial_asset_in_use")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinancialAssetInUse {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_financial_asset", nullable = false)
	private AllFinancialAsset financialAsset;

}
