package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.service.portfolio.history_data.Impl.HistoryPriceServiceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * Interface of {@link HistoryPriceServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface HistoryPriceService {

	void updatePriceAmericanAssetsByExchange(String exchange) throws IOException, ParseException, InterruptedException;

	LinkedList<String> getListTickersToUpdateByExchange(String exchange);
}
