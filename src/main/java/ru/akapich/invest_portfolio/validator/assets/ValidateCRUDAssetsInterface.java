package ru.akapich.invest_portfolio.validator.assets;

import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;

import java.util.List;

/**
 * Interface to validate new assets for {@link NewAssetsForm}
 *
 * @author Aleksandr Marakulin
 **/

public interface ValidateCRUDAssetsInterface {

	NewAssetsForm firstFloatNumberAsset(List<NewAssetsForm> listNewAssetsForm);
	boolean isTickersUnique(List<NewAssetsForm> listNewAssetsForm);
	NewAssetsForm notExistAsset(List<NewAssetsForm> listNewAssetsForm);
	NewAssetsForm assetAlreadyInTheInvestPortfolio(List<NewAssetsForm> listNewAssetsForm);
}
