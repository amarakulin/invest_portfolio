package ru.akapich.invest_portfolio.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;

import java.io.IOException;
import java.text.ParseException;

/**
 * JavaBean object to schedule automatically tasks
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class SchedulePriceAssets {

	@Autowired
	private HistoryPriceService historyPriceService;

	//Every Monday-Friday at 9:59, 10:59, ... 15:59
	@Scheduled(cron = "0 59 9-15 ? * MON-FRI", zone = "UTC-5")
	void updatePriceOfNYSEExchange() throws IOException, ParseException {
		historyPriceService.updatePriceAmericanAssetsByExchange("NYSE");

	}
}
