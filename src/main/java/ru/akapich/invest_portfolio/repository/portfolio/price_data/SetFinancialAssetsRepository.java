package ru.akapich.invest_portfolio.repository.portfolio.price_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.price_data.SetFinancialAssets;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface SetFinancialAssetsRepository extends JpaRepository<SetFinancialAssets, Long> {
}
