package ru.akapich.invest_portfolio.model.portfolio.asset_data.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.FinancialAssetInUse;

import javax.persistence.*;

/**
 * JavaBean object that represents name of currency(USD, EUR etc)
 * of {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_currency")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Currency {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

//	//foreign key
//	@OneToOne//(mappedBy = "t_currency")
//	private FinancialAsset financialAsset;
}
