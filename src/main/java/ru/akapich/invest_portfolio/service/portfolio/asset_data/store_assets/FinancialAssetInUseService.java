package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.FinancialAssetInUseServiceImpl;

/**
 * Interface of {@link FinancialAssetInUseServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface FinancialAssetInUseService {

	FinancialAssetInUse getAndAddToAssetInUseIfNotExist(String ticker);

}
