package ru.akapich.invest_portfolio.service.portfolio.history_data;

import ru.akapich.invest_portfolio.service.portfolio.history_data.Impl.HistoryPriceServiceImpl;

import java.util.List;

/**
 * Interface of {@link HistoryPriceServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface HistoryPriceService {

	void updatePriceAmericanAssets();

	List<String> listTickersToUpdateByExchange(String exchange);
}
