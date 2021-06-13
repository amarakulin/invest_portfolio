package ru.akapich.invest_portfolio.model.price_data;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.akapich.invest_portfolio.model.asset_data.FinancialAsset;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_set_financial_assets")
@AllArgsConstructor
@NoArgsConstructor
@Data
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class SetFinancialAssets {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	Set<Integer> setFinancialAssets;
}
