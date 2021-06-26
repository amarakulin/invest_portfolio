package ru.akapich.invest_portfolio.service.portfolio.category.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.category.CategoryCreateForm;
import ru.akapich.invest_portfolio.model.portfolio.Category;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.CategoryRepository;
import ru.akapich.invest_portfolio.repository.portfolio.InvestPortfolioRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.category.CategoryService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.util.List;
import java.util.Set;

/**
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private InvestPortfolioRepository investPortfolioRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private Environment env;

	@Override
	@Transactional
	public BaseResponseForm addNewCategory(CategoryCreateForm categoryCreateForm) {
		String errorMessage = "";
		Category firstExistCategory = categoryRepository.findFirstByName(categoryCreateForm.getName());
		if (firstExistCategory != null){
			errorMessage = env.getProperty("valid.exist.category");
		}
		else{
			List<OwnedFinancialAsset> ownedFinancialAssets = ownedFinancialAssetRepository
					.findOwnedFinancialAssetsByFinancialAssetInUse_IdAllFinancialAssetTicker(categoryCreateForm.getTickers());//Could be error
			System.out.println(String.format("owned financial asset EXPECTED: %s", categoryCreateForm.getTickers()));
			System.out.println(String.format("owned financial asset GET: %s", ownedFinancialAssets));
//			List<OwnedFinancialAsset> ownedFinancialAssets = ownedFinancialAssetRepository
//					.findOwnedFinancialAssetsByFinancialAssetInUse_IdAllFinancialAssetTicker(categoryCreateForm.getTickers());
			Category category = Category.builder()
					.name(categoryCreateForm.getName())
					.idOwnedFinancialAssets(ownedFinancialAssets)
					.build();
			categoryRepository.save(category);
		}

		return BaseResponseForm.builder()
				.error(errorMessage)
				.resultCode("".equals(errorMessage) ? 0 : 1)
				.build();
	}

	@Override
	public void setCategory(String nameCategory) {
		Category category;
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();

		if ("total".equals(nameCategory)){
			category = null;
		}
		else{
			category = categoryRepository.findFirstByName(nameCategory);
		}
		investPortfolio.setCategory(category);
	}

	@Override
	public void deleteCategory(String nameCategory) {

	}
}
