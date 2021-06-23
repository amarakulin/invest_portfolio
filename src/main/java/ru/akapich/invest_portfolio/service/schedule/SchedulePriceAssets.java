package ru.akapich.invest_portfolio.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.parser.info_assets.america.ParseInfoAmericanStock;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.AllFinancialAssetService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * JavaBean object to schedule automatically tasks
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class SchedulePriceAssets {

	@Autowired
	private HistoryPriceService historyPriceService;

	@Autowired
	private HistoryAmountService historyAmountService;

	@Autowired
	private AllFinancialAssetService allFinancialAssetService;

	@Autowired
	private ParseInfoAmericanStock parseAmericanStock;

	//Every Monday-Friday at 9:10, 10:10, ... 15:10
	@Scheduled(cron = "0 10 9-15 ? * MON-FRI", zone = "GMT-5")
	void updatePriceOfNYSEExchange() throws IOException, ParseException, CloneNotSupportedException, InterruptedException {
		historyPriceService.updatePriceAmericanAssetsByExchange("NYSE");
		historyAmountService.updateAllHistoryAmount();
	}


	//Every Monday-Friday at 9:40
	@Scheduled(cron = "0 40 9 ? * MON-FRI", zone = "GMT-5")
	void updateInfoAssetsExchange() throws IOException {
		List<Map<String, String>> listAssets = parseAmericanStock.getAllStocksByAmericanExchange("NYSE");
		allFinancialAssetService.insertAllAssets(listAssets);
	}
}
