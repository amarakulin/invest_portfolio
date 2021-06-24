package ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.awt.Color;

/**
 * JavaBean domain object that represents a assets that has all users.
 * Collected form {@link AllFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/
@Entity
@Table(name="t_financial_asset_in_use")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FinancialAssetInUse {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String color;//FIXME add unique

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_financial_asset", nullable = false)
	private AllFinancialAsset idAllFinancialAsset;
}
