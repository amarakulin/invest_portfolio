package ru.akapich.invest_portfolio.parcer.load;

import org.springframework.beans.factory.annotation.Autowired;
import ru.akapich.invest_portfolio.parcer.america.ParseAmericanStock;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AllFinancialAssetImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Class that load and insert data assets in database
 *
 * @author Aleksandr Marakulin
 **/

public class LoadInfoAsset {


	@Autowired
	private ParseAmericanStock parseAmericanStock;

	@Autowired
	private AllFinancialAssetImpl allFinancialAsset;

	public void loadInfoAssetsByAmericanExchange(String nameExchange) throws IOException {
		List<Map<String, String>> listAssets = parseAmericanStock.getAllStocksByAmericanExchange(nameExchange);
		allFinancialAsset.insertAllAssets(listAssets);
	}
}
