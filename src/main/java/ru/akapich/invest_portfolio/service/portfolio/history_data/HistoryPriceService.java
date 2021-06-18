package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.service.portfolio.history_data.Impl.HistoryPriceServiceImpl;

import java.io.IOException;

/**
 * Interface of {@link HistoryPriceServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface HistoryPriceService {

	void updatePriceAmericanAssetsByExchange(String exchange) throws IOException;

	String stringTickersToUpdateByExchange(String exchange);
}
