package ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

/**
 * JavaBean object that interaction with Database for {@link }
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface OwnedFinancialAssetRepository extends JpaRepository<OwnedFinancialAsset, Integer> {
}
