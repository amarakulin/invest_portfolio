package ru.akapich.invest_portfolio.controller.modify_assets;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.AssetsResponseForm;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.model.forms.ValidateCRUDAssetsInterface;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.FinancialAssetInUseRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AddingNewListFinancialAssetsImpl;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.math.BigDecimal;
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

			if (allFinancialAssetRepository.findByTickerAndIdTypeAsset_Name(asset.getTicker(), asset.getType()) == null){

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


	private AssetsResponseForm getAssetsResponseForm(List<NewAssetsForm> listAssetsForm){
		String errorMessage = "";

		NewAssetsForm firstNotExistAsset = notExistAsset(listAssetsForm);
		NewAssetsForm assetAlreadyInTheInvestPortfolio = assetAlreadyInTheInvestPortfolio(listAssetsForm);
		if (!isTickersUnique(listAssetsForm)){
			errorMessage = "{valid.assets.repeat}";
		}
		else if (firstNotExistAsset != null){
			errorMessage = "{valid.asset.not_exist} : " + firstNotExistAsset.getTicker();
		}
		else if (assetAlreadyInTheInvestPortfolio != null){
			errorMessage = "{valid.asset.in_portfolio} : " + assetAlreadyInTheInvestPortfolio.getTicker();
		}
		Integer resultCode = errorMessage.equals("") ? 0 : 1;

		return AssetsResponseForm.builder()
				.error(errorMessage)
				.resultCode(resultCode)
				.build();
	}



	@RequestMapping(value = "/api/data/newassets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AssetsResponseForm setNewAssets(@RequestBody List<NewAssetsForm> listAssetsForm, Model model){
		log.info("Start: setNewAssets");
		log.info(String.format("get list with: %s", listAssetsForm.toString()));
		AssetsResponseForm assetsResponseForm = getAssetsResponseForm(listAssetsForm);
		log.info(String.format("Get a result of response error: %s", assetsResponseForm.getError()));
////		//FIXME May be check if user in the session
		if(assetsResponseForm.getResultCode() == 0){
			addingNewListFinancialAsset.addNewAssets(listAssetsForm);
		}
		log.info("End: setNewAssets");
		return assetsResponseForm;
	}

	@PutMapping("/api/asset/edit")
	@ResponseBody
	public String updateAsset(@RequestParam(name="ticker") String ticker,
							@RequestParam(name="amount") BigDecimal amount){
		String response = historyAmountService.updateAssetByTickerWithAmount(ticker, amount);
		return response;
	}
}
