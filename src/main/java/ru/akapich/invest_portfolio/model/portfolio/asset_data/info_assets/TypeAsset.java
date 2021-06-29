package ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;

import javax.persistence.*;

/**
 * JavaBean object that represent all types of {@link AllFinancialAsset}.
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_type_asset")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TypeAsset {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;
}
