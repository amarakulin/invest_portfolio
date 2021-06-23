package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.util.function.Function;
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
		System.out.println("getLineTime:");
		LinkedList<LocalDateTime> uniqueDate = historyAmountRepository.getUniqueTime(investPortfolio);
		LinkedList<String> uniqueDateStringList = uniqueDate.stream()
				.map(u -> Timestamp.valueOf(u).toString())
				.collect(Collectors.toCollection(LinkedList::new));
		uniqueDateStringList.addFirst("time");
		System.out.println(uniqueDateStringList);
		return uniqueDateStringList;
	}

	@Override
	public List<LinkedList<String>> getLinesValuesAssets(InvestPortfolio investPortfolio) {
		System.out.println("getLinesValuesAssets:");
		List<LinkedList<String>> values = new ArrayList<>();

		LinkedList<HistoryAmount> allHistoryAmount = historyAmountRepository.findAllByOwnedFinancialAsset_InvestPortfolio(investPortfolio);
		LinkedList<String> listTickers = allHistoryAmount.stream()
				.map(h -> h.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker())
				.collect(Collectors.toCollection(LinkedList::new));
		for (String ticker : listTickers){
			LinkedList<String> tickersValues = allHistoryAmount.stream()
					.filter(t -> t.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker().equals(ticker))
					.map(v -> v.getTotal().toPlainString())
					.collect(Collectors.toCollection(LinkedList::new));

			tickersValues.addFirst(ticker);
			values.add(tickersValues);
		}
		System.out.println(values);
		return values;
	}

	@Override
	public List<List<String>> getLines(InvestPortfolio investPortfolio) {
		System.out.println("getLines:");

		List<List<String>> lines = new ArrayList<>();

		lines.add(getLineTime(investPortfolio));
		lines.addAll(getLinesValuesAssets(investPortfolio));
		System.out.println(lines);
		return lines;
	}

	@Override
	public Map<String, String> getTypes(InvestPortfolio investPortfolio) {
		System.out.println("getTypes:");

		Map<String, String> mapTypes;

		List<OwnedFinancialAsset> allHistoryAmount = ownedFinancialAssetRepository.findAllByInvestPortfolio(investPortfolio);
		List<String> listTickers = allHistoryAmount.stream()
				.map(h -> h.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker())
				.collect(Collectors.toList());
		mapTypes = listTickers.stream().collect(Collectors.toMap(k -> k, v -> "line"));
		mapTypes.put("total", "total");
		mapTypes.put("time", "time");
		System.out.println(mapTypes);
		return mapTypes;
	}

	@Override
	public Map<String, String> getNames(InvestPortfolio investPortfolio) {
		System.out.println("getNames:");

		Map<String, String> mapNames;

		List<OwnedFinancialAsset> allHistoryAmount = ownedFinancialAssetRepository.findAllByInvestPortfolio(investPortfolio);
		List<AllFinancialAsset> listTickers = allHistoryAmount.stream()
				.map(h -> h.getFinancialAssetInUse().getIdAllFinancialAsset())
				.collect(Collectors.toList());
		mapNames = listTickers.stream().collect(Collectors.toMap(AllFinancialAsset::getTicker, AllFinancialAsset::getTicker));
		mapNames.put("total", "total");
		System.out.println(mapNames);
		return mapNames;
	}

	@Override
	public Map<String, String> getColors(InvestPortfolio investPortfolio) {
		Map<String, String> mapColors;

		System.out.println("getColors:");


		List<OwnedFinancialAsset> historyAmountWithUniqueAssets = ownedFinancialAssetRepository.findAllByInvestPortfolio(investPortfolio);
		mapColors = historyAmountWithUniqueAssets.stream()
				.collect(Collectors.toMap(k -> k.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker()
						, v -> v.getFinancialAssetInUse().getColor()));
		System.out.println(mapColors);
		return mapColors;
	}

	@Override
	public Map<String, String> getPurchaseDate(InvestPortfolio investPortfolio) {
		System.out.println("getPurchaseDate:");

		Map<String, String> mapPurchaseDate;

		List<FormPurchaseDate> historyAmountWithPurchaseDate = historyAmountRepository.getAllPurchaseDateByInvestPortfolio(investPortfolio);
		mapPurchaseDate = historyAmountWithPurchaseDate.stream()
				.collect(Collectors.toMap(k -> k.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker()
						, v -> Timestamp.valueOf(v.getDate()).toString()));
		System.out.println(mapPurchaseDate);
		return mapPurchaseDate;
	}

	@Override
	public FormGraph getGraph() {
		System.out.println("getGraph:");

		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();

		FormGraph formGraph = FormGraph.builder()
				.lines(getLines(investPortfolio))
				.type(getTypes(investPortfolio))
				.names(getNames(investPortfolio))
				.color(getColors(investPortfolio))
				.purchaseDate(getPurchaseDate(investPortfolio))
				.build();
		return formGraph;
	}


//	@Override
//	public List<List<BigDecimal>> getListWithCoordinatesDatePrice(List<FormDatePriceGraphSQLQuery> listFormGraphValues) {
//		List<List<BigDecimal>> values = new ArrayList<>();
//
//		for (FormDatePriceGraphSQLQuery datePriceValue : listFormGraphValues){
//			List<BigDecimal> datePriceList = new ArrayList<>();
//			datePriceList.add(new BigDecimal(Timestamp.valueOf(datePriceValue.getDate()).getTime()));
//			datePriceList.add(datePriceValue.getTotal());
//			values.add(datePriceList);
//		}
//		return values;
//	}
//
//	@Override
//	public List<List<BigDecimal>> getValuesGeneralGraph() {
//		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
//		List<FormDatePriceGraphSQLQuery> valuesForGeneralGraph = historyAmountRepository.getGeneralDatePriceByInvestPortfolio(investPortfolio);
//
//		return getListWithCoordinatesDatePrice(valuesForGeneralGraph);
//	}
//
//	@Override
//	public List<List<BigDecimal>> getValuesGraphByTickerAndInvestPortfolio(String ticker, InvestPortfolio investPortfolio) {
//		List<FormDatePriceGraphSQLQuery> valuesForEachAssetGraph = historyAmountRepository
//			.getAllDatePriceValueByInvestPortfolioAndTicker(investPortfolio, ticker);
//
//		return getListWithCoordinatesDatePrice(valuesForEachAssetGraph);
//	}
//
//
//
//	@Override
//	public List<FormGraph> getAllAssetsGraph() {
//		List<FormGraph> graphData = new ArrayList<>();
//
//		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
//
//		List<String> listTickers = ownedFinancialAssetRepository.findAllTickersByInvestPortfolio(investPortfolio);
//		for (String ticker : listTickers){
//			graphData.add(
//				FormGraph.builder()
//						.ticker(ticker)
//						.values(getValuesGraphByTickerAndInvestPortfolio(ticker, investPortfolio))
//						.build()
//			);
//		}
//		return graphData;
//	}


}
