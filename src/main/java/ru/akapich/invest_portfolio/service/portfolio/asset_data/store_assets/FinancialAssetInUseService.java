package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;

/**
 * @author Aleksandr Marakulin
 **/

public interface FinancialAssetInUseService {

	FinancialAssetInUse getOrAddToAssetInUseIfNotExist(String ticker);

}
