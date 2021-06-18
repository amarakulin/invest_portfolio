package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.service.portfolio.history_data.Impl.HistoryPriceServiceImpl;

import java.io.IOException;
import java.text.ParseException;

/**
 * Interface of {@link HistoryPriceServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface HistoryPriceService {

	void updatePriceAmericanAssetsByExchange(String exchange) throws IOException, ParseException;

	String stringTickersToUpdateByExchange(String exchange);
}
