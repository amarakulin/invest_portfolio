package ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.Exchange;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;

import java.util.List;

/**
 * JavaBean object that interaction with Database for {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface FinancialAssetInUseRepository extends JpaRepository<FinancialAssetInUse, Integer> {

	FinancialAssetInUse findFinancialAssetInUseById(Integer id);
	FinancialAssetInUse findFinancialAssetInUseByIdAllFinancialAsset_Ticker(String ticker);

	@Query("SELECT f FROM FinancialAssetInUse f WHERE f.idAllFinancialAsset.idExchange = ?1")
	List<FinancialAssetInUse> getListTickersToUpdateByExchange(String exchange);

}
