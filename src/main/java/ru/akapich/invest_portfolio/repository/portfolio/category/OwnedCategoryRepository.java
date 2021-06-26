package ru.akapich.invest_portfolio.repository.portfolio.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.category.OwnedCategory;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface OwnedCategoryRepository  extends JpaRepository<OwnedCategory, Long> {

	OwnedCategory findFirstByOwnedFinancialAsset_InvestPortfolioAndCategory_Name(InvestPortfolio investPortfolio, String name);

}
