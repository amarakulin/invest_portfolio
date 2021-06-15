package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

/**
 * @author Aleksandr Marakulin
 **/

public interface OwnedFinancialAssetService {

	OwnedFinancialAsset getAndAddNewOwnedAssetsUser(InvestPortfolio investPortfolio,
													FinancialAssetInUse financialAssetInUse);
}
