package ru.akapich.invest_portfolio.repository.portfolio.history_data;

import jdk.dynalink.linker.LinkerServices;
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

	HistoryAmount findFirstByOrderByOwnedFinancialAssetDesc();

	//TODO Сгрупировать по OwnedFinancialAsset -> сделать row_number ->  и из него взять последний

	//To test
	HistoryAmount findTopByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);

	@Query("SELECT DISTINCT a FROM HistoryAmount a WHERE a.ownedFinancialAsset = ?1")
	HistoryAmount lastAmountByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset);


//	@Query("SELECT ha ROW_NUMBER")
//	List<HistoryAmount> listOfLastAmountByOwnedFinancialAsset();

}
