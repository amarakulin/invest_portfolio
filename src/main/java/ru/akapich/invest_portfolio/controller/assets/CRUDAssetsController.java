package ru.akapich.invest_portfolio.controller.assets;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.assets.EditAssetForm;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.CRUDAssetsService;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AddingNewListFinancialAssetsImpl;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;

import java.util.List;

/**
 * Controller for Crud assets
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
public class CRUDAssetsController {

	@Autowired
	private AddingNewListFinancialAssetsImpl addingNewListFinancialAsset;

	@Autowired
	private HistoryAmountService historyAmountService;

	@Autowired
	private CRUDAssetsService crudAssetsService;


	@RequestMapping(value = "/api/data/newassets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponseForm setNewAssets(@RequestBody List<NewAssetsForm> listAssetsForm, Model model){
		log.info("Start: setNewAssets");
		log.info(String.format("get list with: %s", listAssetsForm.toString()));
		BaseResponseForm baseResponseForm = crudAssetsService.getAssetsResponseForm(listAssetsForm);
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
