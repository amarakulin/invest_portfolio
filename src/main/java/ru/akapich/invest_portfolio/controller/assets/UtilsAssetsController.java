package ru.akapich.invest_portfolio.controller.assets;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.MatchAssetForm;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.UtilsAssetsService;

import java.util.List;

/**
 * JavaBean object needs to work with utility data asset
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
public class UtilsAssetsController {

	@Autowired
	private UtilsAssetsService utilsAssetsService;

	@GetMapping("/api/data/matchassets")
	@ResponseBody
	public List<MatchAssetForm> matchAssets(@RequestParam(name="ticker") String ticker){
		return utilsAssetsService.getListMatchTickers(ticker);
	}

	@GetMapping("/api/data/allassets")
	public List<String> getAllTickersOfUser(){
		return utilsAssetsService.getListAllTickersOfUser();
	}
}

