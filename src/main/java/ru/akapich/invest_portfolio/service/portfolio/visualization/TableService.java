package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.BodyTable;
import ru.akapich.invest_portfolio.model.forms.visualization.FormTable;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.controller.visualization.TableController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Interface of {@link TableController}
 *
 * @author Aleksandr Marakulin
 **/

public interface TableService {

	FormTable getTable();

	Map<String, String> getHeader();

	List<BodyTable> getBodyByInvestPortfolio(InvestPortfolio investPortfolio);

	LinkedList<String> getOrder();
}
