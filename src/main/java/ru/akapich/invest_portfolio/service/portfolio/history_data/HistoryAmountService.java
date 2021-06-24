package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.service.portfolio.history_data.Impl.HistoryAmountServiceImpl;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Interface of {@link HistoryAmountServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface HistoryAmountService {

	void addNewHistoryAmount(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount);

	Set<HistoryAmount> getLastAmountForEachUniqueOwnedAsset();

	void updateAllHistoryAmount() throws CloneNotSupportedException;

	BigDecimal getTotalPriceForOneAsset(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount);

	String updateAssetByTickerWithAmount(String ticker, BigDecimal amount);
}
