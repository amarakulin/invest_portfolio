package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.FormGraph;
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

	LinkedList<String> getLineTime(LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

	List<LinkedList<String>> getLinesValuesAssets(LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

	List<List<String>> getLines(LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

	Map<String, String> getTypes(LinkedList<OwnedFinancialAsset> allOwnedFinancialAsset);

	Map<String, String> getNames(LinkedList<OwnedFinancialAsset> allOwnedFinancialAsset);

	Map<String, String> getColors(LinkedList<OwnedFinancialAsset> allOwnedFinancialAsset);

	Map<String, String> getPurchaseDate(LinkedList<OwnedFinancialAsset> ownedFinancialAssets);

	FormGraph getGraph();
}
