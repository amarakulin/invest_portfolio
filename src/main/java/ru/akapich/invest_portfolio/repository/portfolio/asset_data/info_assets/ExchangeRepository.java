package ru.akapich.invest_portfolio.repository.portfolio.asset_data.info_assets;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.Exchange;

/**
 * JavaBean object that interaction with Database for class {@link Exchange}.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

	Exchange findExchangeByName(String name);
}
