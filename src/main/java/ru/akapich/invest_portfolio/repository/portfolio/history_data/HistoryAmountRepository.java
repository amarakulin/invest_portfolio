package ru.akapich.invest_portfolio.repository.portfolio.history_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.forms.sql.FormPurchaseDate;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQuery;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface HistoryAmountRepository extends JpaRepository<HistoryAmount, Long> {

	@Query("SELECT h FROM HistoryAmount h WHERE h.id = (" +
			"SELECT MAX(h2.id) FROM HistoryAmount h2 WHERE h2.ownedFinancialAsset = ?1 AND h2.ownedFinancialAsset.isDelete = false ) ")
	HistoryAmount lastAmountByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);

	@Query("SELECT SUM (h.total) FROM HistoryAmount h, OwnedFinancialAsset o WHERE" +
			" h.ownedFinancialAsset = o " +
			"AND o.investPortfolio = ?1 " +
			"AND h.amount <> 0" +
			"AND h.date = ?2")
	BigDecimal getTotalPriceOfInvestPortfolio(InvestPortfolio investPortfolio, LocalDateTime date);

	@Query("SELECT SUM (h.total) FROM HistoryAmount h, OwnedCategory o WHERE" +
			" h.ownedFinancialAsset = o.ownedFinancialAsset " +
			"AND o.category = ?1 " +
			"AND h.amount <> 0" +
			"AND h.date = ?2")
	BigDecimal getTotalPriceByCategoryAndDate(Category category, LocalDateTime date);

//	@Query(value = "SELECT new ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQueryInvestPortfolio(h.date, SUM(h.total), h.ownedFinancialAsset.investPortfolio)" +
//			" FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1" +
//			" GROUP BY h.date, h.ownedFinancialAsset.investPortfolio")
//	LinkedList<FormDatePriceGraphSQLQuery> getGeneralDatePriceByInvestPortfolio(InvestPortfolio investPortfolio);

	@Query(value = "SELECT new ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQuery(h.date, SUM(h.total), h.ownedFinancialAsset.investPortfolio)" +
			" FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1 AND h.ownedFinancialAsset IN ?2" +
			" GROUP BY h.date, h.ownedFinancialAsset.investPortfolio")
	LinkedList<FormDatePriceGraphSQLQuery> getGeneralDatePriceByCategory(InvestPortfolio investPortfolio, LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

	List<HistoryAmount> findAllByOwnedFinancialAsset_InvestPortfolioAndDate(InvestPortfolio investPortfolio, LocalDateTime date);

	@Query("SELECT h FROM HistoryAmount h, OwnedCategory o" +
			" WHERE h.ownedFinancialAsset = o.ownedFinancialAsset" +
			" AND o.category = ?1" +
			" AND h.date = ?2")
	List<HistoryAmount> getAllByCategoryAndDate(Category category, LocalDateTime date);

	@Query("SELECT h.date FROM HistoryAmount h WHERE h.ownedFinancialAsset IN ?1 GROUP BY h.date")
	List<LocalDateTime> getUniqueTime(LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

//	LinkedList<HistoryAmount> findAllByOwnedFinancialAsset_InvestPortfolio(InvestPortfolio investPortfolio);

	@Query("SELECT h FROM HistoryAmount h WHERE h.ownedFinancialAsset IN ?1")
	LinkedList<HistoryAmount> getAllByListOwnedFinancialAssets(LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

	@Query("SELECT new ru.akapich.invest_portfolio.model.forms.sql.FormPurchaseDate(h.ownedFinancialAsset,  MIN(h.date), h.ownedFinancialAsset.investPortfolio)" +
			" FROM HistoryAmount h WHERE h.ownedFinancialAsset.investPortfolio = ?1 AND h.ownedFinancialAsset IN ?2" +
			" GROUP BY h.ownedFinancialAsset, h.ownedFinancialAsset.investPortfolio")//FIXME if user delete and after half year buy the same asset what is a purchase date?
	List<FormPurchaseDate> getAllPurchaseDateByInvestPortfolio(InvestPortfolio investPortfolio, LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

	@Query("SELECT h1.date FROM HistoryAmount h1 WHERE h1.id = (" +
			"SELECT MAX(h2.id) FROM HistoryAmount h2 WHERE h2.ownedFinancialAsset.investPortfolio = ?1 AND h2.amount <> 0)")
	LocalDateTime getLastTimeUpdateAssetsByInvestPortfolio(InvestPortfolio investPortfolio);

	@Query("SELECT h1 FROM HistoryAmount h1 WHERE h1.id = (" +
			"SELECT MAX(h2.id) FROM HistoryAmount h2 " +
			"WHERE h2.ownedFinancialAsset.investPortfolio = ?1 " +
			"AND h2.ownedFinancialAsset.FinancialAssetInUse.idAllFinancialAsset.ticker = ?2)")
	HistoryAmount getLastHistoryAmountByInvestPortfolioAndTicker(InvestPortfolio investPortfolio, String ticker);
}
