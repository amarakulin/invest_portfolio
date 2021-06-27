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
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.OwnedFinancialAssetService;
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
	private OwnedFinancialAssetService ownedFinancialAssetService;


	@Override
	public LinkedList<String> getLineTime(LinkedList<OwnedFinancialAsset> ownedFinancialAssets) {
		LinkedList<String> uniqueDateStringList = new LinkedList<>();
		LinkedList<LocalDateTime> uniqueDate = historyAmountRepository.getUniqueTime(ownedFinancialAssets);
		Collections.sort(uniqueDate);//FIXME when total a time list is reversed from biggest to lower
		if (uniqueDate.size() != 0) {
			uniqueDateStringList = uniqueDate.stream()
					.map(u -> String.valueOf(Timestamp.valueOf(u).getTime()))
					.collect(Collectors.toCollection(LinkedList::new));
			uniqueDateStringList.addFirst("time");
		}
		return uniqueDateStringList;
	}

	@Override
	public List<LinkedList<String>> getLinesValuesAssets(LinkedList<OwnedFinancialAsset> ownedFinancialAssets) {
		List<LinkedList<String>> values = new ArrayList<>();
		LinkedList<FormDatePriceGraphSQLQuery> totalValues;
		//All values

		LinkedList<HistoryAmount> allHistoryAmount = historyAmountRepository.getAllByListOwnedFinancialAssets(ownedFinancialAssets);
		if (allHistoryAmount.size() != 0){
			for (OwnedFinancialAsset asset : ownedFinancialAssets){
				String ticker = asset.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker();
				LinkedList<String> tickersValues = allHistoryAmount.stream()
						.filter(t -> t.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset()
								.getTicker().equals(ticker))
						.map(v -> v.getTotal().toPlainString())
						.collect(Collectors.toCollection(LinkedList::new));

				tickersValues.addFirst(ticker);
				values.add(tickersValues);
			}

			//Total values
			totalValues = historyAmountRepository.getGeneralDatePriceByCategory(ownedFinancialAssets.get(0).getInvestPortfolio(), ownedFinancialAssets);
			LinkedList<String> totalValuesString = totalValues.stream()
					.map(v -> v.getTotal().toPlainString()).collect(Collectors.toCollection(LinkedList::new));
			totalValuesString.addFirst("total");
			values.add(totalValuesString);
		}
		return values;
	}

	@Override
	public List<List<String>> getLines(LinkedList<OwnedFinancialAsset> ownedFinancialAssets) {
		List<List<String>> lines = new ArrayList<>();

		lines.add(getLineTime(ownedFinancialAssets));
		lines.addAll(getLinesValuesAssets(ownedFinancialAssets));
		return lines;
	}

	@Override
	public Map<String, String> getTypes(LinkedList<OwnedFinancialAsset> allOwnedFinancialAsset) {
		Map<String, String> mapTypes = new HashMap<>();

		if (allOwnedFinancialAsset.size() != 0){
			List<String> listTickers = allOwnedFinancialAsset.stream()
					.map(h -> h.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker())
					.collect(Collectors.toList());
			mapTypes = listTickers.stream().collect(Collectors.toMap(k -> k, v -> "line"));
			mapTypes.put("total", "line");
			mapTypes.put("time", "time");
		}
		return mapTypes;
	}

	@Override
	public Map<String, String> getNames(LinkedList<OwnedFinancialAsset> allOwnedFinancialAsset) {
		Map<String, String> mapNames = new HashMap<>();

		if (allOwnedFinancialAsset.size() != 0){
			List<AllFinancialAsset> listTickers = allOwnedFinancialAsset.stream()
					.map(h -> h.getFinancialAssetInUse().getIdAllFinancialAsset())
					.collect(Collectors.toList());
			mapNames = listTickers.stream().collect(Collectors.toMap(AllFinancialAsset::getTicker, AllFinancialAsset::getTicker));
			mapNames.put("total", "total");
		}
		return mapNames;
	}

	@Override
	public Map<String, String> getColors(LinkedList<OwnedFinancialAsset> allOwnedFinancialAsset) {
		Map<String, String> mapColors = new HashMap<>();

		if (allOwnedFinancialAsset.size() != 0)
		mapColors = allOwnedFinancialAsset.stream()
				.collect(Collectors.toMap(k -> k.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker()
						, v -> v.getFinancialAssetInUse().getColor()));
		return mapColors;
	}

	@Override
	public Map<String, String> getPurchaseDate(LinkedList<OwnedFinancialAsset> ownedFinancialAssets) {
		Map<String, String> mapPurchaseDate = new HashMap<>();

		List<FormPurchaseDate> historyAmountWithPurchaseDate = historyAmountRepository.getAllPurchaseDateByInvestPortfolio(ownedFinancialAssets.get(0).getInvestPortfolio(), ownedFinancialAssets);
		if (historyAmountWithPurchaseDate.size() != 0) {
			mapPurchaseDate = historyAmountWithPurchaseDate.stream()
					.collect(Collectors.toMap(k -> k.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker()
							, v -> String.valueOf(Timestamp.valueOf(v.getDate()).getTime())));
			LocalDateTime purchaseDateTotal = historyAmountWithPurchaseDate.stream()
					.map(FormPurchaseDate::getDate).min(Comparator.comparing(LocalDateTime::getNano)).get();//FIXME needs is Present

			mapPurchaseDate.put("total", String.valueOf(Timestamp.valueOf(purchaseDateTotal).getTime()));
		}
		return mapPurchaseDate;
	}

	@Override
	public FormGraph getGraph() {
		FormGraph formGraph;


		if (userService.getUserInCurrentSession() == null){
			return null;
		}
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		log.info(String.format("[+] Collecting graph data for user with investPortfolioI: %d", investPortfolio.getId()));
		LinkedList<OwnedFinancialAsset> allOwnedFinancialAssets = ownedFinancialAssetService.getAllOwnedAssetByInvestPortfolioDependsCategory(investPortfolio);

		if (allOwnedFinancialAssets.size() == 0){
			formGraph = FormGraph.builder().build();
		}
		else {
			formGraph = FormGraph.builder()
					.lines(getLines(allOwnedFinancialAssets))
					.types(getTypes(allOwnedFinancialAssets))
					.names(getNames(allOwnedFinancialAssets))
					.color(getColors(allOwnedFinancialAssets))
					.purchaseDate(getPurchaseDate(allOwnedFinancialAssets))
					.build();
		}
		System.out.println(String.format("FormGraph: %s", formGraph));
		log.info(String.format("[+] Finish collect GRAPH for user with investPortfolio '%d'", investPortfolio.getId()));

		return formGraph;
	}
}
