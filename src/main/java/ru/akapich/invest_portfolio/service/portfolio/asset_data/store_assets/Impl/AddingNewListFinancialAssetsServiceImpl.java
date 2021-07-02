package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.AddingNewListFinancialAssetService;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.FinancialAssetInUseService;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.OwnedFinancialAssetService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.util.List;

/**
 * Implementation of {@link AddingNewListFinancialAssetService} interface.
 * Adds new assets in a {@link InvestPortfolio}
 *
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class AddingNewListFinancialAssetsServiceImpl implements AddingNewListFinancialAssetService {

	@Autowired
	private FinancialAssetInUseService financialAssetInUseService;

	@Autowired
	private OwnedFinancialAssetService ownedFinancialAssetService;

	@Autowired
	private HistoryAmountService historyAmountService;

	@Autowired
	private UserService userService;

	@Override
	public void addNewAssets(List<NewAssetsForm> listAssetsForm) {

		InvestPortfolio userInvestPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		log.info(String.format("[+] Get InvPortfolio with id to add new assets: %d", userInvestPortfolio.getId()));
		for (NewAssetsForm assetsForm : listAssetsForm){
			log.info(String.format("Adding asset with ticker: %s", assetsForm.getTicker()));
			FinancialAssetInUse financialAssetInUse = financialAssetInUseService
					.getAndAddToAssetInUseIfNotExist(assetsForm.getTicker());
			log.info(String.format("Get asset with ticker: %s", financialAssetInUse.getIdAllFinancialAsset().getTicker()));

			OwnedFinancialAsset ownedFinancialAsset = ownedFinancialAssetService
					.getAndAddNewOwnedAssetsUserIfNotExist(userInvestPortfolio, financialAssetInUse);
			log.info(String.format("Get ownedFinancialAsset with :ticker %s, inv_p %d", ownedFinancialAsset.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker(), ownedFinancialAsset.getInvestPortfolio().getId()));
			historyAmountService.addNewHistoryAmount(ownedFinancialAsset, assetsForm.getAmount());
		}
		log.info("[+] Adding new assets is done");
	}
}
