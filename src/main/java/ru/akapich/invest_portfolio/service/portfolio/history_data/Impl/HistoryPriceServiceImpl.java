package ru.akapich.invest_portfolio.service.portfolio.history_data.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryPrice;
import ru.akapich.invest_portfolio.parcer.price_assets.america.ParseAmericanPriceAssets;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.FinancialAssetInUseRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryPriceRepository;
import ru.akapich.invest_portfolio.service.date.DateService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class HistoryPriceServiceImpl implements HistoryPriceService {

	@Autowired
	private FinancialAssetInUseRepository financialAssetInUseRepository;

	@Autowired
	private ParseAmericanPriceAssets parseAmericanPriceAssets;

	@Autowired
	private DateService dateService;

	@Autowired
	private HistoryPriceRepository historyPriceRepository;

	@Override
	@Transactional
	public void updatePriceAmericanAssets() throws IOException {
		Map<String, BigDecimal> infoAmericanPriceAssets = parseAmericanPriceAssets.getAllPriceAmericanAssets("NYSE");
		String currentDate = dateService.getCurrentDate();
		for(Map.Entry<String, BigDecimal> asset : infoAmericanPriceAssets.entrySet()){
			historyPriceRepository.save(
					HistoryPrice.builder()
							.idFinancialAssetInUse(financialAssetInUseRepository.findFinancialAssetInUseByIdAllFinancialAsset_Ticker(asset.getKey()))
							.price(asset.getValue())
							.date(currentDate)
							.build()
			);
		}
	}

	@Override
	public String listTickersToUpdateByExchange(String exchange) {
		List<FinancialAssetInUse> listFinancialAssetInUse = financialAssetInUseRepository.getListTickersToUpdateByExchange(exchange);
		List<String> listTickers = listFinancialAssetInUse.stream().map(a -> a.getIdAllFinancialAsset().getTicker()).collect(Collectors.toList());
		System.out.println(String.format("listTickersToUpdateByExchange: %s", listTickers.toString()));
		return String.join(",", listTickers);
	}
}
