package ru.akapich.invest_portfolio.model.portfolio.asset_data.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JavaBean object that represent all types of asset.
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_type_asset")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeAsset {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

//	//foreign key
//	@OneToOne//(mappedBy = "t_type_asset")
//	private FinancialAsset financialAsset;
}
