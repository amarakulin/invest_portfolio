package ru.akapich.invest_portfolio.repository.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;

/**
 * JavaBean object that interaction with Database for class {@link InvestPortfolio}.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface InvestPortfolioRepository extends JpaRepository<InvestPortfolio, Long> {
}
