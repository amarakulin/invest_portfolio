package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQuery;
import ru.akapich.invest_portfolio.model.forms.sql.FormPurchaseDate;
import ru.akapich.invest_portfolio.model.forms.visualization.FormGraph;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.service.portfolio.visualization.GraphService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Implementation of {@link GraphService} interface
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class GraphServiceImpl implements GraphService{

	@Autowired
	private HistoryAmountRepository historyAmountRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;


	@Override
	public LinkedList<String> getLineTime(InvestPortfolio investPortfolio) {
		LinkedList<LocalDateTime> uniqueDate = historyAmountRepository.getUniqueTime(investPortfolio);
		LinkedList<String> uniqueDateStringList = uniqueDate.stream()
				.map(u -> String.valueOf(Timestamp.valueOf(u).getTime()))
				.collect(Collectors.toCollection(LinkedList::new));
		uniqueDateStringList.addFirst("time");
		return uniqueDateStringList;
	}

	@Override
	public List<LinkedList<String>> getLinesValuesAssets(InvestPortfolio investPortfolio) {
		List<LinkedList<String>> values = new ArrayList<>();
		//All values
		LinkedList<HistoryAmount> allHistoryAmount = historyAmountRepository.findAllByOwnedFinancialAsset_InvestPortfolio(investPortfolio);
		List<String> listTickers = ownedFinancialAssetRepository.findAllTickersByInvestPortfolio(investPortfolio);
		for (String ticker : listTickers){
			LinkedList<String> tickersValues = allHistoryAmount.stream()
					.filter(t -> t.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker().equals(ticker))
					.map(v -> v.getTotal().toPlainString())
					.collect(Collectors.toCollection(LinkedList::new));

			tickersValues.addFirst(ticker);
			values.add(tickersValues);
		}

		//Total values
		LinkedList<FormDatePriceGraphSQLQuery> totalValues = historyAmountRepository.getGeneralDatePriceByInvestPortfolio(investPortfolio);
		LinkedList<String> totalValuesString = totalValues.stream()
				.map(v -> v.getTotal().toPlainString()).collect(Collectors.toCollection(LinkedList::new));
		totalValuesString.addFirst("total");
		values.add(totalValuesString);
		return values;
	}

	@Override
	public List<List<String>> getLines(InvestPortfolio investPortfolio) {
		List<List<String>> lines = new ArrayList<>();

		lines.add(getLineTime(investPortfolio));
		lines.addAll(getLinesValuesAssets(investPortfolio));
		return lines;
	}

	@Override
	public Map<String, String> getTypes(InvestPortfolio investPortfolio) {
		Map<String, String> mapTypes;

		List<OwnedFinancialAsset> allHistoryAmount = ownedFinancialAssetRepository.findAllByInvestPortfolio(investPortfolio);
		List<String> listTickers = allHistoryAmount.stream()
				.map(h -> h.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker())
				.collect(Collectors.toList());
		mapTypes = listTickers.stream().collect(Collectors.toMap(k -> k, v -> "line"));
		mapTypes.put("total", "line");
		mapTypes.put("time", "time");
		return mapTypes;
	}

	@Override
	public Map<String, String> getNames(InvestPortfolio investPortfolio) {
		Map<String, String> mapNames;

		List<OwnedFinancialAsset> allHistoryAmount = ownedFinancialAssetRepository.findAllByInvestPortfolio(investPortfolio);
		List<AllFinancialAsset> listTickers = allHistoryAmount.stream()
				.map(h -> h.getFinancialAssetInUse().getIdAllFinancialAsset())
				.collect(Collectors.toList());
		mapNames = listTickers.stream().collect(Collectors.toMap(AllFinancialAsset::getTicker, AllFinancialAsset::getTicker));
		mapNames.put("total", "total");
		return mapNames;
	}

	@Override
	public Map<String, String> getColors(InvestPortfolio investPortfolio) {
		Map<String, String> mapColors;

		List<OwnedFinancialAsset> historyAmountWithUniqueAssets = ownedFinancialAssetRepository.findAllByInvestPortfolio(investPortfolio);
		mapColors = historyAmountWithUniqueAssets.stream()
				.collect(Collectors.toMap(k -> k.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker()
						, v -> v.getFinancialAssetInUse().getColor()));
		return mapColors;
	}

	@Override
	public Map<String, String> getPurchaseDate(InvestPortfolio investPortfolio) {
		Map<String, String> mapPurchaseDate;

		List<FormPurchaseDate> historyAmountWithPurchaseDate = historyAmountRepository.getAllPurchaseDateByInvestPortfolio(investPortfolio);
		mapPurchaseDate = historyAmountWithPurchaseDate.stream()
				.collect(Collectors.toMap(k -> k.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker()
						, v -> String.valueOf(Timestamp.valueOf(v.getDate()).getTime())));
		LocalDateTime purchaseDateTotal = historyAmountWithPurchaseDate.stream()
				.map(FormPurchaseDate::getDate).min(Comparator.comparing(LocalDateTime::getNano)).get();//Fix me needs is Present

		mapPurchaseDate.put("total", String.valueOf(Timestamp.valueOf(purchaseDateTotal).getTime()));

		return mapPurchaseDate;
	}

	@Override
	public FormGraph getGraph() {
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		log.info(String.format("[+] Collecting graph data for user with investPortfolioI: %d", investPortfolio.getId()));
		FormGraph formGraph = FormGraph.builder()
				.lines(getLines(investPortfolio))
				.types(getTypes(investPortfolio))
				.names(getNames(investPortfolio))
				.color(getColors(investPortfolio))
				.purchaseDate(getPurchaseDate(investPortfolio))
				.build();

		log.info(String.format("FormGraph: %s", formGraph));
		return formGraph;
	}
}
