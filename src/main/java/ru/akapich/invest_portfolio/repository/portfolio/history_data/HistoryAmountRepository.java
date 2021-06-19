package ru.akapich.invest_portfolio.repository.portfolio.history_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;

import java.util.List;

/**
 * @author Aleksandr Marakulin
 **/

@Repository
public interface HistoryAmountRepository extends JpaRepository<HistoryAmount, Long> {

	@Query("SELECT DISTINCT h.ownedFinancialAsset FROM HistoryAmount h")
	List<OwnedFinancialAsset> findAllUniqueOwnedAssets();

	@Query("SELECT a FROM HistoryAmount a WHERE a.id = (" +
			"SELECT MAX(a2.id) FROM HistoryAmount a2 WHERE a2.ownedFinancialAsset = ?1) ")
	HistoryAmount lastAmountByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);

}
