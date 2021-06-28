package ru.akapich.invest_portfolio.model.forms.assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Object to get info asset to change the asset
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditAssetForm {

	private String ticker;
	private BigDecimal amount;
}
