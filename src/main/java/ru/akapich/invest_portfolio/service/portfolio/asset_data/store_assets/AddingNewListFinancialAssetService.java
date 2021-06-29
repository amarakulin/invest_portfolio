package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AddingNewListFinancialAssetsServiceImpl;
import java.util.List;

/**
 * Interface for class {@link AddingNewListFinancialAssetsServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/


public interface AddingNewListFinancialAssetService {

	void addNewAssets(List<NewAssetsForm> listAssetsForm);
}
