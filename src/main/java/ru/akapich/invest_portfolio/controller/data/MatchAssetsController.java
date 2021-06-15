package ru.akapich.invest_portfolio.controller.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.MatchAssetForm;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.AllFinancialAssetRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class needs for search a new asset
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PropertySource("classpath:message.properties")
public class MatchAssetsController {

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@GetMapping("/api/data/matchassets")
	@ResponseBody
	public List<MatchAssetForm> matchAssets(@RequestParam(name="ticker") String ticker){
		List<MatchAssetForm> listMatchAssetsForm = new ArrayList<>();

		List<AllFinancialAsset> listMatchesTickers = allFinancialAssetRepository.findTop10ByTickerStartingWithIgnoreCase(ticker);
		log.info(String.format("[+] Get response for match asset with ticker - '%s'", ticker));
		for (var asset :listMatchesTickers){
			listMatchAssetsForm.add(
					MatchAssetForm.builder()
							.name(asset.getName())
							.ticker(asset.getTicker())
							.type(asset.getIdTypeAsset().getName())
							.build()
			);
		}
		return listMatchAssetsForm;
	}

}

