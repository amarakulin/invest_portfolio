package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.CRUDAssetsService;
import ru.akapich.invest_portfolio.validator.ValidateCRUDAssetsInterface;

import java.util.List;

/**
 * Implementation of {@link CRUDAssetsService} interface
 *
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class CRUDAssetsServiceImpl implements CRUDAssetsService{

	@Autowired
	private Environment env;

	@Autowired
	private ValidateCRUDAssetsInterface validateCRUDAssetsInterface;

	@Override
	public BaseResponseForm getAssetsResponseForm(List<NewAssetsForm> listAssetsForm) {
		String errorMessage = "";

		NewAssetsForm firstNotExistAsset = validateCRUDAssetsInterface.notExistAsset(listAssetsForm);
		NewAssetsForm assetAlreadyInTheInvestPortfolio = validateCRUDAssetsInterface.assetAlreadyInTheInvestPortfolio(listAssetsForm);
		NewAssetsForm firstFloatNumberAsset = validateCRUDAssetsInterface.firstFloatNumberAsset(listAssetsForm);
		if (!validateCRUDAssetsInterface.isTickersUnique(listAssetsForm)){
			errorMessage = String.format("%s", env.getProperty("valid.assets.repeat"));
		}
		else if (firstNotExistAsset != null){
			errorMessage = String.format("%s: %s", env.getProperty("valid.asset.not_exist"), firstNotExistAsset.getTicker());
		}
		else if (assetAlreadyInTheInvestPortfolio != null){
			errorMessage = String.format("%s: %s", env.getProperty("valid.asset.in_portfolio"), assetAlreadyInTheInvestPortfolio.getTicker());
		}
		else if (firstFloatNumberAsset != null){
			errorMessage = String.format("%s: %s", env.getProperty("valid.not_integer"), firstFloatNumberAsset.getTicker());
		}
		Integer resultCode = errorMessage.equals("") ? 0 : 1;

		return BaseResponseForm.builder()
				.error(errorMessage)
				.resultCode(resultCode)
				.build();
	}
}
