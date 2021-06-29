package ru.akapich.invest_portfolio.model.portfolio.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import javax.persistence.*;

/**
 * JavaBean domain object that represents asset for a special {@link Category}
 *
 * @author Aleksandr Marakulin
 **/

@Entity
@Table(name="t_owned_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OwnedCategory {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_category", nullable = false)
	private Category category;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_owned_financial_asset", nullable = false)
	private OwnedFinancialAsset ownedFinancialAsset;
}
