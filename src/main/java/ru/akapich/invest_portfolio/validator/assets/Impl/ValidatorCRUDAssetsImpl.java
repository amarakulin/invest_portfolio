package ru.akapich.invest_portfolio.validator.assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.controller.assets.CRUDAssetsController;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.FinancialAssetInUseRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.utils.MathUtils;
import ru.akapich.invest_portfolio.validator.assets.ValidateCRUDAssetsInterface;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaBean object to validate data from {@link CRUDAssetsController}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Component
public class ValidatorCRUDAssetsImpl implements ValidateCRUDAssetsInterface {

	@Autowired
	private Environment env;

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private FinancialAssetInUseRepository financialAssetInUseRepository;

	@Override
	public NewAssetsForm firstFloatNumberAsset(List<NewAssetsForm> listNewAssetsForm) {
		NewAssetsForm firstFloatNumberAsset;

		firstFloatNumberAsset = null;
		for(NewAssetsForm assetsForm : listNewAssetsForm){
			System.out.println(String.format("New Asset: %s", assetsForm));
			if (!assetsForm.getType().equals(env.getProperty("type.crypto")) && !MathUtils.isIntegerValue(assetsForm.getAmount())){
				firstFloatNumberAsset = assetsForm;
				break;
			}
		}
		return firstFloatNumberAsset;
	}

	@Override
	public boolean isTickersUnique(List<NewAssetsForm> listNewAssetsForm) {
		int sizeUniqueAssets = listNewAssetsForm.stream()
				.map(NewAssetsForm::getTicker)
				.collect(Collectors.toSet())
				.size();
		return sizeUniqueAssets == listNewAssetsForm.size();
	}

	@Override
	public NewAssetsForm notExistAsset(List<NewAssetsForm> listNewAssetsForm) {
		NewAssetsForm firstNotExistAssets;

		firstNotExistAssets = null;
		for (NewAssetsForm asset : listNewAssetsForm){
			System.out.println(String.format("notExistAsset search for ticker: %s",  asset.getTicker()));

			if (allFinancialAssetRepository.findByTicker(asset.getTicker()) == null){// FIXME Not unique exception(VCF)

				firstNotExistAssets = asset;
				break;
			}
		}
		log.info(String.format("[+] notExistAsset result is: %s", firstNotExistAssets));
		return firstNotExistAssets;
	}

	@Override
	public NewAssetsForm assetAlreadyInTheInvestPortfolio(List<NewAssetsForm> listNewAssetsForm) {
		NewAssetsForm assetInInvestPortfolio;

		assetInInvestPortfolio = null;
		InvestPortfolio userInvestPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		if (userInvestPortfolio == null){
			log.info("User not in the session");
		}
		for (NewAssetsForm asset : listNewAssetsForm){
			if (ownedFinancialAssetRepository.findExistTickerInInvestPortfolio(
					userInvestPortfolio,
					financialAssetInUseRepository.findFinancialAssetInUseByIdAllFinancialAsset_Ticker(asset.getTicker()))
					!= null){
				assetInInvestPortfolio = asset;
				break;
			}
		}
		return assetInInvestPortfolio;
	}
}
