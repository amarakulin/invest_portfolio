package ru.akapich.invest_portfolio.service.portfolio.history_data.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryPrice;
import ru.akapich.invest_portfolio.parser.price_assets.america.ParseAmericanPriceAssets;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.FinancialAssetInUseRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryPriceRepository;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of {@link HistoryPriceService}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class HistoryPriceServiceImpl implements HistoryPriceService {

	@Autowired
	private FinancialAssetInUseRepository financialAssetInUseRepository;

	@Autowired
	@Lazy
	private ParseAmericanPriceAssets parseAmericanPriceAssets;

	@Autowired
	private HistoryPriceRepository historyPriceRepository;

	@Override
	@Transactional
	public void updatePriceAmericanAssetsByExchange(String exchange) throws IOException, InterruptedException {
		FinancialAssetInUse financialAssetInUse;
		System.out.println(String.format("updatePriceAmericanAssetsByExchange with exchange: %s", exchange));
		Map<String, BigDecimal> infoAmericanPriceAssets = parseAmericanPriceAssets.getAllPriceAmericanAssets(exchange);
		if (infoAmericanPriceAssets == null){
			log.info("[-] Couldn't get a tickers from parseAmericanPriceAssets");
			return;
		}
		for(Map.Entry<String, BigDecimal> asset : infoAmericanPriceAssets.entrySet()){
			financialAssetInUse = financialAssetInUseRepository.findFinancialAssetInUseByIdAllFinancialAsset_Ticker(asset.getKey());
			if (financialAssetInUse == null) {
				log.warn(String.format("[-] Couldn't update price american assets on asset with ticker: '%s'", asset.getKey()));
				return;
			}
			HistoryPrice historyPrice = historyPriceRepository.findByIdFinancialAssetInUse(financialAssetInUse);
			if (historyPrice == null) {
				historyPriceRepository.save(
						HistoryPrice.builder()
								.idFinancialAssetInUse(financialAssetInUseRepository.findFinancialAssetInUseByIdAllFinancialAsset_Ticker(asset.getKey()))
								.price(asset.getValue())
								.build()
				);
			}
			else{
				historyPrice.setPrice(asset.getValue());
			}

		}
	}

	@Override
	public LinkedList<String> getListTickersToUpdateByExchange(String exchange) {
		System.out.println(String.format("Start stringTickersToUpdateByExchange with exchange: %s", exchange));
		List<FinancialAssetInUse> listFinancialAssetInUse = financialAssetInUseRepository.getListTickersToUpdateByExchange(exchange);
		LinkedList<String> listTickers = listFinancialAssetInUse.stream().map(a -> a.getIdAllFinancialAsset().getTicker()).collect(Collectors.toCollection(LinkedList::new));
		return listTickers;
	}
}
