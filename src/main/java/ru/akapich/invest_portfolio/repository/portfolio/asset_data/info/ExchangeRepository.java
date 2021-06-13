package ru.akapich.invest_portfolio.repository.portfolio.asset_data.info;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.Exchange;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
}
