package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.NewAssetsForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.AddingNewListFinancialAsset;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.FinancialAssetInUseService;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.OwnedFinancialAssetService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.utils.UtilsUser;

import java.util.List;

/**
 * Implementation of {@link AddingNewListFinancialAsset} interface.
 * Adds new assets in a {@link InvestPortfolio}
 *
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class AddingNewListFinancialAssetsImpl implements AddingNewListFinancialAsset {

	@Autowired
	private FinancialAssetInUseService financialAssetInUseService;

	@Autowired
	private OwnedFinancialAssetService ownedFinancialAssetService;

	@Autowired
	private HistoryAmountService historyAmountService;

	@Autowired
	private UtilsUser utilsUser;

	@Override
	public void addNewAssets(List<NewAssetsForm> listAssetsForm) {
		log.info("Start addNewAssets !!!!!!!!!");
		InvestPortfolio userInvestPortfolio = utilsUser.getUserInCurrentSession().getInvestPortfolio();
		if (userInvestPortfolio == null){
			log.info("User not in the session");
			return;
		}
		log.info(String.format("Get InvPortfolio with id : %d", userInvestPortfolio.getId()));
		for (NewAssetsForm assetsForm : listAssetsForm){
			log.info(String.format("Adding asset with ticker: %s", assetsForm.getTicker()));
			FinancialAssetInUse financialAssetInUse = financialAssetInUseService
					.getAndAddToAssetInUseIfNotExist(assetsForm.getTicker());
			log.info(String.format("Get asset with ticker: %s", financialAssetInUse.getIdAllFinancialAsset().getTicker()));

			OwnedFinancialAsset ownedFinancialAsset = ownedFinancialAssetService
					.getAndAddNewOwnedAssetsUser(userInvestPortfolio, financialAssetInUse);
			log.info(String.format("Get ownedFinancialAsset with :ticker %s, inv_p %d", ownedFinancialAsset.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker(), ownedFinancialAsset.getInvestPortfolio().getId()));
			historyAmountService.addNewHistoryAmount(ownedFinancialAsset, assetsForm.getAmount());
		}
		log.info("Done motherfucker!!!");
	}
}
