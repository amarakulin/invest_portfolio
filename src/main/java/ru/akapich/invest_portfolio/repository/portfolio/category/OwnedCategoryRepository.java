package ru.akapich.invest_portfolio.repository.portfolio.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import ru.akapich.invest_portfolio.model.portfolio.category.OwnedCategory;

import java.util.LinkedList;
import java.util.List;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface OwnedCategoryRepository  extends JpaRepository<OwnedCategory, Long> {

	OwnedCategory findFirstByOwnedFinancialAsset_InvestPortfolioAndCategory_Name(InvestPortfolio investPortfolio, String name);

	@Query("SELECT c.ownedFinancialAsset FROM OwnedCategory c WHERE c.category = ?1")
	LinkedList<OwnedFinancialAsset> getAllOwnedFinancialAssetByCategory(Category category);

	@Query("SELECT DISTINCT c.category.name FROM OwnedCategory c WHERE c.ownedFinancialAsset.investPortfolio = ?1")
	List<String> getAllNamesCategoriesByInvestPortfolio(InvestPortfolio investPortfolio);
}
