package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.forms.NewAssetsForm;

import java.util.List;

/**
 * @author Aleksandr Marakulin
 **/


public interface AddingNewListFinancialAsset {

	void addNewAssets(List<NewAssetsForm> listAssetsForm);
}
