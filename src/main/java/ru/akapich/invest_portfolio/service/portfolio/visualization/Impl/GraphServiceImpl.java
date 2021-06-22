package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.sql.FormDatePriceGraphSQLQuery;
import ru.akapich.invest_portfolio.model.forms.visualization.FormGraphAllAsserts;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
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
	public List<List<BigDecimal>> getListWithCoordinatesDatePrice(List<FormDatePriceGraphSQLQuery> listFormGraphValues) {
		List<List<BigDecimal>> values = new ArrayList<>();

		for (FormDatePriceGraphSQLQuery datePriceValue : listFormGraphValues){
			List<BigDecimal> datePriceList = new ArrayList<>();
			datePriceList.add(new BigDecimal(Timestamp.valueOf(datePriceValue.getDate()).getTime()));
			datePriceList.add(datePriceValue.getTotal());
			values.add(datePriceList);
		}
		return values;
	}

	@Override
	public List<List<BigDecimal>> getValuesGeneralGraph() {
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		List<FormDatePriceGraphSQLQuery> valuesForGeneralGraph = historyAmountRepository.getGeneralDatePriceByInvestPortfolio(investPortfolio);

		return getListWithCoordinatesDatePrice(valuesForGeneralGraph);
	}

	@Override
	public List<List<BigDecimal>> getValuesGraphByTickerAndInvestPortfolio(String ticker, InvestPortfolio investPortfolio) {
		List<FormDatePriceGraphSQLQuery> valuesForEachAssetGraph = historyAmountRepository
			.getAllDatePriceValueByInvestPortfolioAndTicker(investPortfolio, ticker);

		return getListWithCoordinatesDatePrice(valuesForEachAssetGraph);
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
