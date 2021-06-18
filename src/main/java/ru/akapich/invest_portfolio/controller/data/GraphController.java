package ru.akapich.invest_portfolio.controller.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akapich.invest_portfolio.parser.info_assets.america.ParseInfoAmericanStock;
import ru.akapich.invest_portfolio.parser.price_assets.america.ParseAmericanPriceAssets;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AllFinancialAssetImpl;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Controller for 's pages.//TODO
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PropertySource("classpath:message.properties")
public class GraphController {

	@Autowired
	private ParseInfoAmericanStock parseAmericanStock;

	@Autowired
	private AllFinancialAssetImpl allFinancialAsset;

	@Autowired
	private ParseAmericanPriceAssets parseAmericanPriceAssets;

	@Autowired
	private HistoryPriceService historyPriceService;


	@GetMapping("/api/data/graph")
	public String graph() throws IOException {
		List<Map<String, String>> listAssets= parseAmericanStock.getAllStocksByAmericanExchange("NYSE");
		allFinancialAsset.insertAllAssets(listAssets);

		return "Parsed Stock";
	}

	@GetMapping("api/data/updateprice")
	public String update() throws IOException, ParseException {
		System.out.println("Start api/data/updateprice");
		historyPriceService.updatePriceAmericanAssetsByExchange("NYSE");
		return "Succes update";
	}
}
