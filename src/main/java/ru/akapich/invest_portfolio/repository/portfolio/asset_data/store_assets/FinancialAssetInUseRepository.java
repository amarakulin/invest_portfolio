package ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import java.util.List;

/**
 * JavaBean object that interaction with Database for {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface FinancialAssetInUseRepository extends JpaRepository<FinancialAssetInUse, Integer> {

	FinancialAssetInUse findFinancialAssetInUseByIdAllFinancialAsset_Ticker(String ticker);

	@Query("SELECT f FROM FinancialAssetInUse f WHERE f.idAllFinancialAsset.idExchange.name = ?1")
	List<FinancialAssetInUse> getListTickersToUpdateByExchange(String exchange);

	@Query("SELECT o.FinancialAssetInUse FROM OwnedFinancialAsset o WHERE o = ?1 AND o.isDelete = false")
	FinancialAssetInUse findByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);
}
