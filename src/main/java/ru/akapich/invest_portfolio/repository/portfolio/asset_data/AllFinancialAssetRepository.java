package ru.akapich.invest_portfolio.repository.portfolio.asset_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.AllFinancialAsset;

import java.util.List;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface AllFinancialAssetRepository extends JpaRepository<AllFinancialAsset, Integer> {

	List<AllFinancialAsset> findTop10ByTickerStartingWithIgnoreCase(String ticker);

}
