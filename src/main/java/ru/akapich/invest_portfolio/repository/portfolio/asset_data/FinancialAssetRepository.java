package ru.akapich.invest_portfolio.repository.portfolio.asset_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.FinancialAssetInUse;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface FinancialAssetRepository extends JpaRepository<FinancialAssetInUse, Integer> {
}
