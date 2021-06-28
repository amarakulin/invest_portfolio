package ru.akapich.invest_portfolio.controller.assets;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.assets.EditAssetForm;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.model.forms.ValidateCRUDAssetsInterface;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.FinancialAssetInUseRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AddingNewListFinancialAssetsImpl;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.utils.MathUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for Crud assets
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
public class CRUDAssetsController implements ValidateCRUDAssetsInterface {

	//FIXME Set validation in separate class !!!!!!!!!

	@Autowired
	private AddingNewListFinancialAssetsImpl addingNewListFinancialAsset;

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private FinancialAssetInUseRepository financialAssetInUseRepository;

	@Autowired
	private HistoryAmountService historyAmountService;

	@Autowired
	private UserService userService;

	@Autowired
	private Environment env;

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

	private BaseResponseForm getAssetsResponseForm(List<NewAssetsForm> listAssetsForm){
		String errorMessage = "";

		NewAssetsForm firstNotExistAsset = notExistAsset(listAssetsForm);
		NewAssetsForm assetAlreadyInTheInvestPortfolio = assetAlreadyInTheInvestPortfolio(listAssetsForm);
		NewAssetsForm firstFloatNumberAsset = firstFloatNumberAsset(listAssetsForm);
		if (!isTickersUnique(listAssetsForm)){
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



	@RequestMapping(value = "/api/data/newassets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponseForm setNewAssets(@RequestBody List<NewAssetsForm> listAssetsForm, Model model){
		log.info("Start: setNewAssets");
		log.info(String.format("get list with: %s", listAssetsForm.toString()));
		BaseResponseForm baseResponseForm = getAssetsResponseForm(listAssetsForm);
		log.info(String.format("Get a result of response error: %s", baseResponseForm.getError()));
		if(baseResponseForm.getResultCode() == 0){
			addingNewListFinancialAsset.addNewAssets(listAssetsForm);
		}
		log.info("End: setNewAssets");
		return baseResponseForm;
	}

	@PutMapping("/api/asset/edit")
	@ResponseBody
	public BaseResponseForm updateAsset(@RequestBody EditAssetForm editAssetForm){
		BaseResponseForm response = historyAmountService.updateAssetByTickerWithAmount(editAssetForm);
		return response;
	}

	@DeleteMapping("/api/asset/delete")
	@ResponseBody
	public void deleteAsset(@RequestParam(name="ticker") String ticker){
		historyAmountService.deleteAssetByTicker(ticker);
	}
}
