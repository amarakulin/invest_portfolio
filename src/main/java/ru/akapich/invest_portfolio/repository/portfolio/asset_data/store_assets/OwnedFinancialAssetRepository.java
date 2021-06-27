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

	@Query("SELECT o FROM OwnedFinancialAsset o WHERE o.investPortfolio = ?1 and o.FinancialAssetInUse = ?2")
	OwnedFinancialAsset findExistTickerInInvestPortfolio(
			InvestPortfolio investPortfolio,
			FinancialAssetInUse financialAssetInUse);

	@Query("SELECT o.FinancialAssetInUse FROM OwnedFinancialAsset o WHERE o = ?1")
	FinancialAssetInUse findFinancialAssetInUseByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);

	@Query("SELECT o.FinancialAssetInUse.idAllFinancialAsset.ticker FROM OwnedFinancialAsset o WHERE o.investPortfolio = ?1")
	List<String> findAllTickersByInvestPortfolio(InvestPortfolio investPortfolio);

	LinkedList<OwnedFinancialAsset> findAllByInvestPortfolio(InvestPortfolio investPortfolio);

	@Query("SELECT DISTINCT o FROM OwnedFinancialAsset o")
	List<OwnedFinancialAsset> findAllUniqueOwnedAssets();

//	List<OwnedFinancialAsset> findOwnedFinancialAssetsByFinancialAssetInUse_IdAllFinancialAssetTicker(List<String> tickers);
//	List<OwnedFinancialAsset> findOwnedFinancialAssetsByFinancialAssetInUse_IdAllFinancialAsset_Ticker(List<String> tickers);

	@Query("SELECT o FROM OwnedFinancialAsset o WHERE o.investPortfolio = ?1 AND o.FinancialAssetInUse.idAllFinancialAsset.ticker IN ?2")
	List<OwnedFinancialAsset> getOwnedFinancialAssetsByListTickersAndInvestPortfolio(InvestPortfolio investPortfolio, List<String> tickers);

}
