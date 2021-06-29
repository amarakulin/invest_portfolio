package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryPrice;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

/**
 * Interface of {@link HistoryPrice}
 *
 * @author Aleksandr Marakulin
 **/

public interface HistoryPriceService {

	void updatePriceAmericanAssetsByExchange(String exchange) throws IOException, ParseException, InterruptedException;

	LinkedList<String> getListTickersToUpdateByExchange(String exchange);
}
