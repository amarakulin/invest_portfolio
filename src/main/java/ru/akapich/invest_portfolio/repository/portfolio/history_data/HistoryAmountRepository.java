package ru.akapich.invest_portfolio.repository.portfolio.history_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface HistoryAmountRepository extends JpaRepository<HistoryAmount, Long> {

	@Query("SELECT DISTINCT h.ownedFinancialAsset FROM HistoryAmount h")
	List<OwnedFinancialAsset> findAllUniqueOwnedAssets();

	@Query("SELECT a FROM HistoryAmount a WHERE a.id = (" +
			"SELECT MAX(a2.id) FROM HistoryAmount a2 WHERE a2.ownedFinancialAsset = ?1) ")
	HistoryAmount lastAmountByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);

	@Query("SELECT SUM (p.total) FROM HistoryAmount p, OwnedFinancialAsset o WHERE" +
			" p.ownedFinancialAsset = o " +
			"AND o.investPortfolio = ?1 " +
			"AND p.date = ?2")
	BigDecimal getTotalPriceOfInvestPortfolio(InvestPortfolio investPortfolio, LocalDateTime date);

	@Query("SELECT h.date, SUM (h.amount) FROM HistoryAmount h GROUP BY h.date HAVING h.ownedFinancialAsset.investPortfolio = ?1")
	List<HistoryAmount> getAllHistoryAmountOfDateByInvestPortfolio(InvestPortfolio investPortfolio);

	Set<HistoryAmount> findAllByOwnedFinancialAsset_InvestPortfolioAndDate(InvestPortfolio investPortfolio, LocalDateTime date);

	List<HistoryAmount> findAllByOwnedFinancialAsset_InvestPortfolioAndOwnedFinancialAsset_FinancialAssetInUse_IdAllFinancialAsset_Ticker(
						InvestPortfolio investPortfolio, String ticker);



}
