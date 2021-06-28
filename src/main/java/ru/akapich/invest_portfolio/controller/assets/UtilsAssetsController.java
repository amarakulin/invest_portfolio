package ru.akapich.invest_portfolio.controller.assets;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.MatchAssetForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class needs for search a new asset
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
public class UtilsAssetsController {

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/api/data/matchassets")
	@ResponseBody
	public List<MatchAssetForm> matchAssets(@RequestParam(name="ticker") String ticker){
		List<MatchAssetForm> listMatchAssetsForm = new ArrayList<>();

		List<AllFinancialAsset> listMatchesTickers = allFinancialAssetRepository.findTop10ByTickerStartingWithIgnoreCaseOrderByTicker(ticker);
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

	@GetMapping("/api/data/allassets")
	public List<String> getAllTickersOfUser(){
		List<String> allTickersOfUser = new ArrayList<>();
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		LinkedList<OwnedFinancialAsset> listOwnedFinancialAssetLinked = ownedFinancialAssetRepository
				.findAllByInvestPortfolioDeleteFalse(investPortfolio);
		if (listOwnedFinancialAssetLinked != null && listOwnedFinancialAssetLinked.size() != 0){
			allTickersOfUser = listOwnedFinancialAssetLinked.stream()
					.map(o -> o.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker())
					.collect(Collectors.toList());
		}
		return allTickersOfUser;
	}

}

