package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;

/**
 * Interface of {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

public interface FinancialAssetInUseService {

	FinancialAssetInUse getAndAddToAssetInUseIfNotExist(String ticker);

}
