package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.assets.EditAssetForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.service.portfolio.history_data.Impl.HistoryAmountServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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

	BaseResponseForm updateAssetByTickerWithAmount(EditAssetForm editAssetForm);

	void deleteAssetByTicker(String ticker);

	List<HistoryAmount> getAllByDateAndInvestPortfolioDependsCategory(InvestPortfolio investPortfolio, LocalDateTime date);

	BigDecimal getLatestTotalPriceByInvestPortfolioDependsCategory(InvestPortfolio investPortfolio);
}
