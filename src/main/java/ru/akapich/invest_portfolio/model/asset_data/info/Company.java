package ru.akapich.invest_portfolio.model.asset_data.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.asset_data.FinancialAsset;

import javax.persistence.*;

/**
 * JavaBean object that represents info a company of
 * {@link FinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_company")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String ticker;

	//foreign key
	@OneToOne(mappedBy = "t_company")
	private FinancialAsset financialAsset;
}
