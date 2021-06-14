package ru.akapich.invest_portfolio.service.portfolio.asset_data;

import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.common.AssertionFailure;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.AllFinancialAsset;
import ru.akapich.invest_portfolio.parcer.america.ParseAmericanStock;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.info.CurrencyService;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.info.ExchangeService;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.info.TypeAssetService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class AllFinancialAssetImpl implements AllFinancialAssetService {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private ExchangeService exchangeService;

	@Autowired
	private TypeAssetService typeAssetService;

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Override
	@Transactional
	public void insertAllAssets(List<Map<String, String>> listAssets) {
		Set<AllFinancialAsset> allFinancialAssets = new HashSet<>();
		for (Map<String, String> asset : listAssets) {
				AllFinancialAsset assetToInsert = AllFinancialAsset.builder()
						.ticker(asset.get("symbol"))
						.name(asset.get("name"))
						.idCurrency(currencyService.findOrAddCurrencyByName(asset.get("currency")))
						.idExchange(exchangeService.findOrAddExchangeByName(asset.get("exchange")))
						.idTypeAsset(typeAssetService.findOrAddExchangeByName(asset.get("type")))
						.build();
				if (!allFinancialAssets.add(assetToInsert)) {
					log.info(String.format("[-] Set! Try to add unique ticker '%s'", asset.get("symbol")));
				}
		}
		allFinancialAssetRepository.saveAllAndFlush(allFinancialAssets);
	}


}




















