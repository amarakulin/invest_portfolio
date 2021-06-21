package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.FormGraphAllAsserts;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.GraphServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface of {@link GraphServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface GraphService {

	List<List<BigDecimal>> getValuesGeneralGraph();

	List<List<BigDecimal>> getValuesGraphByTickerAndInvestPortfolio(String ticker, InvestPortfolio investPortfolio);

	List<List<BigDecimal>> getListWithCoordinatesDatePrice(List<HistoryAmount> listHistoryAmount);

	List<FormGraphAllAsserts> getAllAssetsGraph();
}
