package ru.akapich.invest_portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_set_financial_assets")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetFinancialAssets {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	Set<FinancialAsset> financialAssets;
}
