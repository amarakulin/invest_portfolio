package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.assets.MatchAssetForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.UtilsAssetsService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UtilsAssetsService}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class UtilsAssetsServiceImpl implements UtilsAssetsService {

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private UserService userService;

	@Override
	public List<MatchAssetForm> getListMatchTickers(String tickerToMatch) {
		List<MatchAssetForm> listMatchAssetsForm = new ArrayList<>();

		List<AllFinancialAsset> listMatchesTickers = allFinancialAssetRepository.findTop10ByTickerStartingWithIgnoreCaseOrderByTicker(tickerToMatch);
		log.info(String.format("[+] Get response for match asset with ticker - '%s'", tickerToMatch));
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

	@Override
	public List<String> getListAllTickersOfUser() {
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
