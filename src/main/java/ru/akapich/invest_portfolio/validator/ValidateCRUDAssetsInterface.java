package ru.akapich.invest_portfolio.validator;

import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;

import java.util.List;

/**
 * Interface to validate new assets for {@link NewAssetsForm}
 *
 * @author Aleksandr Marakulin
 **/

public interface ValidateCRUDAssetsInterface {

	//TODO Create method to check if an Asset already in the invest portfolio

	NewAssetsForm firstFloatNumberAsset(List<NewAssetsForm> listNewAssetsForm);
	boolean isTickersUnique(List<NewAssetsForm> listNewAssetsForm);
	NewAssetsForm notExistAsset(List<NewAssetsForm> listNewAssetsForm);
	NewAssetsForm assetAlreadyInTheInvestPortfolio(List<NewAssetsForm> listNewAssetsForm);
}
