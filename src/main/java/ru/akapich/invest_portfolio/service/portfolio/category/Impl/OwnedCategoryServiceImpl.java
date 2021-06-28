package ru.akapich.invest_portfolio.service.portfolio.category.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import ru.akapich.invest_portfolio.model.portfolio.category.OwnedCategory;
import ru.akapich.invest_portfolio.repository.portfolio.category.OwnedCategoryRepository;
import ru.akapich.invest_portfolio.service.portfolio.category.OwnedCategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link OwnedCategoryService} interface
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class OwnedCategoryServiceImpl implements OwnedCategoryService{

	@Autowired
	private OwnedCategoryRepository ownedCategoryRepository;

	@Override
	public void addNewOwnedCategoriesByOwnedFinancialAssetAndCategory(List<OwnedFinancialAsset> ownedFinancialAssets, Category category) {
		List<OwnedCategory> listOwnedCategories = new ArrayList<>();
		for(OwnedFinancialAsset asset : ownedFinancialAssets){
			listOwnedCategories.add(OwnedCategory.builder()
					.category(category)
					.ownedFinancialAsset(asset)
					.build());
		}
		if (listOwnedCategories.size() != 0){
			ownedCategoryRepository.saveAll(listOwnedCategories);
		}
	}
}
