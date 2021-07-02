package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import java.util.LinkedList;

/**
 * Interface for {@link OwnedFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

public interface OwnedFinancialAssetService {

	OwnedFinancialAsset getAndAddNewOwnedAssetsUserIfNotExist(InvestPortfolio investPortfolio,
	                                                          FinancialAssetInUse financialAssetInUse);

	LinkedList<OwnedFinancialAsset> getAllOwnedAssetByInvestPortfolioDependsCategory(InvestPortfolio investPortfolio);
}
