package ru.akapich.invest_portfolio.service.portfolio.category.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.category.CategoryCreateForm;
import ru.akapich.invest_portfolio.model.portfolio.category.Category;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.category.OwnedCategory;
import ru.akapich.invest_portfolio.repository.portfolio.category.CategoryRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.category.OwnedCategoryRepository;
import ru.akapich.invest_portfolio.service.portfolio.category.CategoryService;
import ru.akapich.invest_portfolio.service.portfolio.category.OwnedCategoryService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link CategoryService} interface
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OwnedCategoryRepository ownedCategoryRepository;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private OwnedCategoryService ownedCategoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private Environment env;

	@Override
	public BaseResponseForm getResponseCreateCategory(CategoryCreateForm categoryCreateForm) {

		String errorMessage = "";
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();

		OwnedCategory firstOwnedExistCategory = ownedCategoryRepository.findFirstByOwnedFinancialAsset_InvestPortfolioAndCategory_Name(investPortfolio, categoryCreateForm.getName());

		if (firstOwnedExistCategory != null){
			errorMessage = env.getProperty("valid.category.exist");
		}
		else if ("total".equals(categoryCreateForm.getName())){
			errorMessage = env.getProperty("valid.category.main");
		}
		else if (categoryCreateForm.getValue().size() == 0){
			errorMessage = env.getProperty("valid.category.not_exist_tickers");
		}

		return BaseResponseForm.builder()
				.error(errorMessage)
				.resultCode("".equals(errorMessage) ? 0 : 1)
				.build();
	}

	@Override
	@Transactional
	public void saveNewCategory(CategoryCreateForm categoryCreateForm) {
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		List<OwnedFinancialAsset> ownedFinancialAssets = ownedFinancialAssetRepository
				.getAllOwnedFinancialAssetsByListTickersAndInvestPortfolio(investPortfolio, categoryCreateForm.getValue());

		Category category = Category.builder()
				.name(categoryCreateForm.getName())
				.build();

		categoryRepository.save(category);
		ownedCategoryService.addNewOwnedCategoriesByOwnedFinancialAssetAndCategory(ownedFinancialAssets, category);
	}

	@Override
	public List<String> getListNameCategories() {
		List<String> listNameCategories;
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		listNameCategories = ownedCategoryRepository.getAllNamesCategoriesByInvestPortfolio(investPortfolio);
		if (listNameCategories == null){
			listNameCategories = new ArrayList<>();
		}
		return listNameCategories;
	}

	@Override
	@Transactional
	public void setCategory(String nameCategory) {
		Category category;
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		if ("total".equals(nameCategory)){
			category = null;
		}
		else{
			OwnedCategory firstOwnedExistCategory = ownedCategoryRepository
					.findFirstByOwnedFinancialAsset_InvestPortfolioAndCategory_Name(investPortfolio, nameCategory);
			if (firstOwnedExistCategory != null){
				category = firstOwnedExistCategory.getCategory();
			}
			else{
				log.info(String.format("[-] Error can't find a category with name: %s", nameCategory));
				category = null;
			}

		}
		investPortfolio.setCategory(category);
	}

	@Override
	public Map<String, String> getCurrentCategory() {
		Category category = userService.getUserInCurrentSession().getInvestPortfolio().getCategory();
		Map<String, String> categoryName = new HashMap<>();
		categoryName.put("category", category == null ? "total" : category.getName());
		return categoryName;
	}
}
