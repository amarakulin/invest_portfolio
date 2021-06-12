package ru.akapich.invest_portfolio.model.asset_data.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.asset_data.FinancialAsset;

import javax.persistence.*;

/**
 * JavaBean object that represents the marked Exchange
 * of {@link FinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_exchange")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exchange {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	//foreign key
	@OneToOne//(mappedBy = "t_exchange")
	private FinancialAsset financialAsset;
}
