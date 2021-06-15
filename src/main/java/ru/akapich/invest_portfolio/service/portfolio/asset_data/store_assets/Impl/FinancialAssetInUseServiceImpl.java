package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.FinancialAssetInUseRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.FinancialAssetInUseService;

/**
 *
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class FinancialAssetInUseServiceImpl implements FinancialAssetInUseService {

	@Autowired
	private FinancialAssetInUseRepository financialAssetInUseRepository;

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Override
	@Transactional
	public FinancialAssetInUse getOrAddToAssetInUseIfNotExist(String ticker) {
		log.info("addToAssetInUseIfNotExist start searching");
		FinancialAssetInUse matchedAsset = financialAssetInUseRepository.
				findFinancialAssetInUseByIdAllFinancialAsset_Ticker(ticker);

		if (matchedAsset == null){
			log.info("addToAssetInUseIfNotExist couldn't find a ticker 'in use'");
			matchedAsset = FinancialAssetInUse.builder()
					.idAllFinancialAsset(allFinancialAssetRepository.findByTicker(ticker))
					.build();
			log.info(String.format("addToAssetInUseIfNotExist add new assert 'in use': %s", ticker));
		}
		financialAssetInUseRepository.save(matchedAsset);
		return matchedAsset;
	}

}
