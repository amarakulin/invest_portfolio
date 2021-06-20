package ru.akapich.invest_portfolio.controller.modify_assets;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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

//	@Autowired
//	private ParseInfoAmericanStock parseAmericanStock;
//
//	@Autowired
//	private AllFinancialAssetImpl allFinancialAsset;
//
//	@Autowired
//	private ParseAmericanPriceAssets parseAmericanPriceAssets;

//	@Autowired
//	private HistoryPriceService historyPriceService;
//
//	@Autowired
//	private HistoryAmountService historyAmountService;


//	@GetMapping("/api/data/graph")
//	public String graph() throws IOException {
////		List<Map<String, String>> listAssets= parseAmericanStock.getAllStocksByAmericanExchange("NYSE");
////		allFinancialAsset.insertAllAssets(listAssets);
//
//		return "Parsed Stock";
//	}

//	@GetMapping("api/data/updateprice")
//	public String update() throws IOException, ParseException, CloneNotSupportedException {
//		System.out.println("Start api/data/updateprice");
//		historyPriceService.updatePriceAmericanAssetsByExchange("NYSE");
//		historyAmountService.updateAllHistoryAmount();
//		return "Succes update";
//	}
}
