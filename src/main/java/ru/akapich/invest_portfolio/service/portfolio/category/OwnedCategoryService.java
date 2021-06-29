package ru.akapich.invest_portfolio.service.portfolio.category;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import ru.akapich.invest_portfolio.model.portfolio.category.OwnedCategory;
import java.util.List;

/**
 * Interface for {@link OwnedCategory}
 *
 * @author Aleksandr Marakulin
 **/

public interface OwnedCategoryService {

	void addNewOwnedCategoriesByOwnedFinancialAssetAndCategory(List<OwnedFinancialAsset> ownedFinancialAssets, Category category);
}
