package ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import java.util.LinkedList;
import java.util.List;

/**
 * JavaBean object that interaction with Database for {@link OwnedFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface OwnedFinancialAssetRepository extends JpaRepository<OwnedFinancialAsset, Integer> {

	LinkedList<OwnedFinancialAsset> findAllByInvestPortfolio(InvestPortfolio investPortfolio);

	@Query("SELECT o FROM OwnedFinancialAsset o WHERE o.investPortfolio = ?1" +
			" AND o.FinancialAssetInUse = ?2" +
			" AND o.isDelete = false ")
	OwnedFinancialAsset findExistTickerInInvestPortfolio(InvestPortfolio investPortfolio, FinancialAssetInUse financialAssetInUse);

	@Query("SELECT o FROM OwnedFinancialAsset o WHERE o.investPortfolio = ?1" +
			" AND o.isDelete = false ")
	LinkedList<OwnedFinancialAsset> findAllByInvestPortfolioDeleteFalse(InvestPortfolio investPortfolio);

	@Query("SELECT DISTINCT o FROM OwnedFinancialAsset o WHERE o.isDelete = false")
	List<OwnedFinancialAsset> findAllUniqueOwnedAssets();

	@Query("SELECT o FROM OwnedFinancialAsset o WHERE o.isDelete = false" +
			" AND o.investPortfolio = ?1" +
			" AND o.FinancialAssetInUse.idAllFinancialAsset.ticker = ?2")
	OwnedFinancialAsset findByInvestPortfolioAndTickerDeleteFalse(InvestPortfolio investPortfolio, String ticker);

	@Query("SELECT o FROM OwnedFinancialAsset o WHERE o.isDelete = true " +
			"AND o.investPortfolio = ?1 " +
			"AND o.FinancialAssetInUse.idAllFinancialAsset.ticker = ?2")
	OwnedFinancialAsset findByInvestPortfolioAndTickerDeleteTrue(InvestPortfolio investPortfolio, String ticker);

	@Query("SELECT o FROM OwnedFinancialAsset o WHERE o.investPortfolio = ?1" +
			" AND o.isDelete = false" +
			" AND o.FinancialAssetInUse.idAllFinancialAsset.ticker IN ?2")
	List<OwnedFinancialAsset> getAllOwnedFinancialAssetsByListTickersAndInvestPortfolio(InvestPortfolio investPortfolio, List<String> tickers);
}
