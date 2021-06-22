package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.visualization.FormGeneralGraphSQLQuery;
import ru.akapich.invest_portfolio.model.forms.visualization.FormGraphAllAsserts;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.service.portfolio.visualization.GraphService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


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
	public List<List<BigDecimal>> getListWithCoordinatesDatePrice(List<HistoryAmount> listHistoryAmount) {
		List<List<BigDecimal>> values = new ArrayList<>();

		for (HistoryAmount historyAmount : listHistoryAmount){
			List<BigDecimal> datePriceList = new ArrayList<>();
			datePriceList.add(new BigDecimal(Timestamp.valueOf(historyAmount.getDate()).getTime()));
			datePriceList.add(historyAmount.getTotal());
			values.add(datePriceList);
		}
		return values;
	}

	@Override
	public List<List<BigDecimal>> getValuesGeneralGraph() {
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();

		System.out.println("HERE!!!");
//		List<HistoryAmount> historyAmountList = historyAmountRepository.getAllHistoryAmountOfDateByInvestPortfolio(investPortfolio);
		List<FormGeneralGraphSQLQuery> test = historyAmountRepository.getAllHistoryAmountOfDateByInvestPortfolio(investPortfolio);
//		List<Object> test = historyAmountRepository.getAllHistoryAmountOfDateByInvestPortfolio(investPortfolio);
		System.out.println("Get data from db!!!");

		System.out.println("PASS IT!!!");
		System.out.println(test);

		List<List<BigDecimal>> values = new ArrayList<>();

		for (FormGeneralGraphSQLQuery aaaaa : test){
			List<BigDecimal> datePriceList = new ArrayList<>();
			datePriceList.add(new BigDecimal(Timestamp.valueOf(aaaaa.getDate()).getTime()));
			datePriceList.add(aaaaa.getTotal());
			values.add(datePriceList);
		}

		return values;
	}

	@Override
	public List<List<BigDecimal>> getValuesGraphByTickerAndInvestPortfolio(String ticker, InvestPortfolio investPortfolio) {
		List<HistoryAmount> allHistoryAmount = historyAmountRepository
			.findAllByInvestPortfolioAndTicker(investPortfolio, ticker);

		return getListWithCoordinatesDatePrice(allHistoryAmount);
	}



	@Override
	public List<FormGraphAllAsserts> getAllAssetsGraph() {
		List<FormGraphAllAsserts> graphData = new ArrayList<>();

		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();

		List<String> listTickers = ownedFinancialAssetRepository.findAllTickersByInvestPortfolio(investPortfolio);
		for (String ticker : listTickers){
			graphData.add(
				FormGraphAllAsserts.builder()
						.ticker(ticker)
						.values(getValuesGraphByTickerAndInvestPortfolio(ticker, investPortfolio))
						.build()
			);
		}
		return graphData;
	}
}
