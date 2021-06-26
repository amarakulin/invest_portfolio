package ru.akapich.invest_portfolio.repository.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//	@Query("SELECT c FROM Category c, InvestPortfolio i " +
//			"WHERE i.category = c " +
//			"AND  i = ?1 " +
//			"AND c.name = ?2 ")
//	Category getCategoryByNameAndInvestPortfolio(InvestPortfolio investPortfolio, String name);


}
