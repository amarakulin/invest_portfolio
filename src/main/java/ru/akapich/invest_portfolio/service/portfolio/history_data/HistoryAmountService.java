package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import java.math.BigDecimal;

/**
 * @author Aleksandr Marakulin
 **/

public interface HistoryAmountService {

	void addNewHistoryAmount(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount);
}
