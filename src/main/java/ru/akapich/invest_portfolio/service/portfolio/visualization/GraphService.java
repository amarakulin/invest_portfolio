package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.FormGraph;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.GraphServiceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Interface of {@link GraphServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface GraphService {

//	List<List<BigDecimal>> getValuesGeneralGraph();
//
//	List<List<BigDecimal>> getValuesGraphByTickerAndInvestPortfolio(String ticker, InvestPortfolio investPortfolio);
//
//	List<List<BigDecimal>> getListWithCoordinatesDatePrice(List<FormDatePriceGraphSQLQuery> listFormGraphValues);
//
//	List<FormGraph> getAllAssetsGraph();

	LinkedList<String> getLineTime(InvestPortfolio investPortfolio);

	List<LinkedList<String>> getLinesValuesAssets(InvestPortfolio investPortfolio);

	List<List<String>> getLines(InvestPortfolio investPortfolio);

	Map<String, String> getTypes(InvestPortfolio investPortfolio);

	Map<String, String> getNames(InvestPortfolio investPortfolio);

	Map<String, String> getColors(InvestPortfolio investPortfolio);

	Map<String, String> getPurchaseDate(InvestPortfolio investPortfolio);


	FormGraph getGraph();
}
