package ru.akapich.invest_portfolio.repository.portfolio.history_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryPrice;

/**
 * JavaBean object that interaction with Database for class {@link HistoryPrice}.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface HistoryPriceRepository extends JpaRepository<HistoryPrice, Long> {

	HistoryPrice findByIdFinancialAssetInUse(FinancialAssetInUse financialAssetInUse);
}
