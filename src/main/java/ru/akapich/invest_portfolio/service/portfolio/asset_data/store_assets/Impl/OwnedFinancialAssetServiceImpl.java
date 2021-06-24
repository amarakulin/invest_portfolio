package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.OwnedFinancialAssetService;

/**
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class OwnedFinancialAssetServiceImpl implements OwnedFinancialAssetService {

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Override
	@Transactional
	public OwnedFinancialAsset getAndAddNewOwnedAssetsUser(InvestPortfolio investPortfolio, FinancialAssetInUse financialAssetInUse) {
		log.info(String.format("getAndAddNewOwnedAssetsUser add with InvPort: %d | ticker financialInUse: %s", investPortfolio.getId(), financialAssetInUse.getIdAllFinancialAsset().getTicker()));
		OwnedFinancialAsset ownedFinancialAsset = OwnedFinancialAsset.builder()
				.investPortfolio(investPortfolio)
				.FinancialAssetInUse(financialAssetInUse)
				.build();
		ownedFinancialAssetRepository.save(ownedFinancialAsset);
		return ownedFinancialAsset;
	}
}
