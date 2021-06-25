package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.FormGraph;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
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

	LinkedList<String> getLineTime(InvestPortfolio investPortfolio);

	List<LinkedList<String>> getLinesValuesAssets(InvestPortfolio investPortfolio);

	List<List<String>> getLines(InvestPortfolio investPortfolio);

	Map<String, String> getTypes(List<OwnedFinancialAsset> allOwnedFinancialAsset);

	Map<String, String> getNames(List<OwnedFinancialAsset> allOwnedFinancialAsset);

	Map<String, String> getColors(List<OwnedFinancialAsset> allOwnedFinancialAsset);

	Map<String, String> getPurchaseDate(InvestPortfolio investPortfolio);

	FormGraph getGraph();
}
