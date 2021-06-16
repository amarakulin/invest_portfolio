package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AddingNewListFinancialAssetsImpl;
import java.util.List;

/**
 * Interface for class {@link AddingNewListFinancialAssetsImpl}
 *
 * @author Aleksandr Marakulin
 **/


public interface AddingNewListFinancialAsset {

	void addNewAssets(List<NewAssetsForm> listAssetsForm);
}
