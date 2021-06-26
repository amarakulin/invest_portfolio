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

import java.util.List;

/**
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
	@Transactional
	public BaseResponseForm addNewCategory(CategoryCreateForm categoryCreateForm) {
		String errorMessage = "";
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		System.out.println(String.format("investPortfolio: %s", investPortfolio));

		OwnedCategory firstOwnedExistCategory = ownedCategoryRepository.findFirstByOwnedFinancialAsset_InvestPortfolioAndCategory_Name(investPortfolio, categoryCreateForm.getName());
		System.out.println(String.format("firstOwnedExistCategory: %s", firstOwnedExistCategory));
		List<OwnedFinancialAsset> ownedFinancialAssets = ownedFinancialAssetRepository
				.getOwnedFinancialAssetsByListTickersAndInvestPortfolio(investPortfolio, categoryCreateForm.getValues());
		System.out.println(String.format("owned financial asset EXPECTED: %s", categoryCreateForm.getValues()));
		System.out.println(String.format("owned financial asset GET: %s", ownedFinancialAssets));

		if (firstOwnedExistCategory != null){
			errorMessage = env.getProperty("valid.category.exist");
		}
		else if ("total".equals(categoryCreateForm.getName())){
			errorMessage = env.getProperty("valid.category.main");
		}
		else if (ownedFinancialAssets.size() == 0){
			errorMessage = env.getProperty("valid.category.not_exist_tickers");
		}
		else{
			Category category = Category.builder()
					.name(categoryCreateForm.getName())
					.build();
			System.out.println(String.format("Create category: %s", category));
			categoryRepository.save(category);
			ownedCategoryService.addNewOwnedCategoriesByOwnedFinancialAssetAndCategory(ownedFinancialAssets, category);
		}
		return BaseResponseForm.builder()
				.error(errorMessage)
				.resultCode("".equals(errorMessage) ? 0 : 1)
				.build();
	}

	@Override
	@Transactional
	public void setCategory(String nameCategory) {
		Category category;
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		System.out.println(String.format("setCategory search for name: %s", nameCategory));
		if ("total".equals(nameCategory)){
			category = null;
		}
		else{
			System.out.println("Not a total!");
			OwnedCategory firstOwnedExistCategory = ownedCategoryRepository
					.findFirstByOwnedFinancialAsset_InvestPortfolioAndCategory_Name(investPortfolio, nameCategory);
			System.out.println(String.format("firstOwnedExistCategory: %s", firstOwnedExistCategory));
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
	public void deleteCategory(String nameCategory) {

	}
}
