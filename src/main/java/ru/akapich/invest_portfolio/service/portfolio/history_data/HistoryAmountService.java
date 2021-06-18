package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.service.portfolio.history_data.Impl.HistoryAmountServiceImpl;
import java.math.BigDecimal;

/**
 * Interface of {@link HistoryAmountServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface HistoryAmountService {

	void addNewHistoryAmount(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount);
}
