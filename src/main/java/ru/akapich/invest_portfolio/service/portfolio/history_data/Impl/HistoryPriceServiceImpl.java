package ru.akapich.invest_portfolio.service.portfolio.history_data.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.FinancialAssetInUseRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryPriceRepository;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;

import java.util.List;

/**
 * @author Aleksandr Marakulin
 **/

public class HistoryPriceServiceImpl implements HistoryPriceService {

	@Autowired
	private FinancialAssetInUseRepository financialAssetInUseRepository;

	@Autowired
	private HistoryPriceRepository historyPriceRepository;

	@Override
	public void updatePriceAmericanAssets() {

	}

	@Override
	public List<String> listTickersToUpdateByExchange(String exchange) {
		return null;
	}
}
