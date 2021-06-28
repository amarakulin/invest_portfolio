package ru.akapich.invest_portfolio.repository.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;

import java.util.Optional;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface InvestPortfolioRepository extends JpaRepository<InvestPortfolio, Long> {

	InvestPortfolio findFirstById(Long id);
}
