package ru.akapich.invest_portfolio.model.forms.assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.controller.assets.UtilsAssetsController;

/**
 * Form response for {@link UtilsAssetsController}
 *
 * @author Aleksandr Marakulin
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MatchAssetForm {

	private String ticker;
	private String name;
	private String type;
}
