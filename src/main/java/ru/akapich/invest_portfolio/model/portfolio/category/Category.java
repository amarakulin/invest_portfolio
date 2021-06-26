package ru.akapich.invest_portfolio.model.portfolio.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="t_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(
//			name = "meta_category",
//			joinColumns = @JoinColumn(name = "id_category"),
//			inverseJoinColumns = @JoinColumn(name = "id_owner_financial_asset"))
//	private List<OwnedFinancialAsset> idOwnedFinancialAssets;


//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "id_owned_financial_asset", nullable = false)
//	private FinancialAssetInUse idFinancialAssetInUse;
}
