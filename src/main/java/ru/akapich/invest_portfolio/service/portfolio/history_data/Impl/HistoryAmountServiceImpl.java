package ru.akapich.invest_portfolio.service.portfolio.history_data.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.forms.assets.AssetsResponseForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryPrice;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryPriceRepository;
import ru.akapich.invest_portfolio.service.date.DateService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.utils.MathUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link HistoryAmountService} interface.
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class HistoryAmountServiceImpl implements HistoryAmountService {

	@Autowired
	private HistoryAmountRepository historyAmountRepository;

	@Autowired
	private HistoryPriceRepository historyPriceRepository;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private DateService dateService;

	@Override
	public BigDecimal getTotalPriceForOneAsset(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount) {
		HistoryPrice historyPriceForOneAsset;
		BigDecimal priceForOneAsset = BigDecimal.ZERO;

		FinancialAssetInUse financialAssetInUse = ownedFinancialAsset.getFinancialAssetInUse();
		log.info(String.format("[+] getTotalPriceForOneAsset ticker '%s'", financialAssetInUse.getIdAllFinancialAsset().getTicker()));
		historyPriceForOneAsset = historyPriceRepository.findByIdFinancialAssetInUse(financialAssetInUse);
		log.info(String.format("[+] getTotalPriceForOneAsset history_price '%s'", historyPriceForOneAsset));
		if (historyPriceForOneAsset != null){
			priceForOneAsset = historyPriceForOneAsset.getPrice();
		}
		return amount.multiply(priceForOneAsset);
	}

	private String stringValidateAmountByTypeOfAsset(String typeAsset, BigDecimal amount){
		String resultError = "";
		if (!typeAsset.equals("{type.crypto}") && !MathUtils.isIntegerValue(amount)){
			resultError = "{valid.amount.not_integer}";
		}
		else if (amount.compareTo(BigDecimal.ZERO) < 0){
			resultError = "{valid.amount.negative}";
		}
		else if (amount.compareTo(BigDecimal.ZERO) == 0){
			resultError = "{valid.amount.cant_delete}";
		}
		return resultError;
	}

	@Override
	@Transactional
	public AssetsResponseForm updateAssetByTickerWithAmount(String ticker, BigDecimal amount) {
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		log.info(String.format("[+] Ticker: '%s' updating with amount: '%f' by invest portfolio: %s", ticker, amount, investPortfolio.getId()));
		HistoryAmount historyAmount = historyAmountRepository.getLastHistoryAmountByInvestPortfolioAndTicker(investPortfolio, ticker);

		String typeAsset = historyAmount.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getIdTypeAsset().getName();
		HistoryPrice historyPriceOfAsset = historyPriceRepository.findByIdFinancialAssetInUse(historyAmount.getOwnedFinancialAsset().getFinancialAssetInUse());

		String errorMessage = stringValidateAmountByTypeOfAsset(typeAsset, amount);
		int resultCode = 0;
		if (!errorMessage.equals("")){
			resultCode = 1;
			log.info(String.format("[-] Ticker: '%s' failed on validation by invest portfolio: %s", ticker, investPortfolio.getId()));
		}
		else{
			historyAmount.setAmount(amount);
			historyAmount.setTotal(historyPriceOfAsset.getPrice().multiply(amount));
			log.info(String.format("[+] Ticker: '%s' successfully update by invest portfolio: %s", ticker, investPortfolio.getId()));
		}
		return AssetsResponseForm.builder()
				.error(errorMessage)
				.resultCode(resultCode)
				.build();
	}

	@Override
	@Transactional
	public String deleteAssetByTicker(String ticker) {
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		log.info(String.format("[+] Ticker: '%s' deleting by invest portfolio: %s", ticker, investPortfolio.getId()));
		HistoryAmount historyAmount = historyAmountRepository.getLastHistoryAmountByInvestPortfolioAndTicker(investPortfolio, ticker);
		historyAmount.setAmount(BigDecimal.ZERO);
		ownedFinancialAssetRepository.delete(historyAmount.getOwnedFinancialAsset());
		//TODO delete asset from FinancialAssetInUse if no one has it anymore!
		return String.format("Success delete ticker: %s", ticker);
	}

	@Override
	@Transactional
	public void addNewHistoryAmount(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount) {

		LocalDateTime date = dateService.getCurrentTime();//FIXME Handle if date is not a work time of exchange
		log.info(String.format("Current date in addNewHistoryAmount '%s'", date));
		BigDecimal totalPriceForOneAsset = getTotalPriceForOneAsset(ownedFinancialAsset, amount);

		log.info(String.format("addNewHistoryAmount: ticker ownedFinancialAsset %s | amount %f | date %s | total %f",
				ownedFinancialAsset.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker(),
				amount, date, totalPriceForOneAsset));
		HistoryAmount historyAmount = HistoryAmount.builder()
				.ownedFinancialAsset(ownedFinancialAsset)
				.amount(amount)
				.total(totalPriceForOneAsset)
				.date(date)
				.build();

		historyAmountRepository.save(historyAmount);
	}

	@Override
	public Set<HistoryAmount> getLastAmountForEachUniqueOwnedAsset() {
		//TODO optimaze query !!!
		Set<HistoryAmount> setLastAmount = new HashSet<>();
		HistoryAmount tmpHistoryAmount;
		System.out.println("In getLastAmountForEachUniqueOwnedAsset()");
		List<OwnedFinancialAsset> uniqueOwnedAssets = ownedFinancialAssetRepository.findAllUniqueOwnedAssets();
		for (OwnedFinancialAsset asset : uniqueOwnedAssets){
			tmpHistoryAmount = historyAmountRepository.lastAmountByOwnedFinancialAsset(asset);
			if (tmpHistoryAmount.getAmount().compareTo(BigDecimal.ZERO) == 0){
				log.warn(String.format("Ticker: '%s' has been deleted for ownedAsset with id: '%d'", asset.getFinancialAssetInUse().getIdAllFinancialAsset().getName(), asset.getId()));
			}
			else if (!setLastAmount.add(tmpHistoryAmount)){
				log.warn(String.format("Repeat value of OwnedFinancialAsset '%d' in unique order",  asset.getId()));
			}
		}
		return setLastAmount;
	}

	@Override
	@Transactional
	public void updateAllHistoryAmount() throws CloneNotSupportedException {
		HistoryAmount historyAmountCopy;
		LocalDateTime currentDate  = dateService.getCurrentTime();
		Set<HistoryAmount> setLastAmount = getLastAmountForEachUniqueOwnedAsset();
		for (HistoryAmount historyAmount: setLastAmount){
			if (!historyAmount.getDate().equals(currentDate)){
				historyAmountCopy = (HistoryAmount) historyAmount.clone();//TODO handle exception
				historyAmountCopy.setDate(currentDate);
				historyAmountCopy.setTotal(getTotalPriceForOneAsset(historyAmount.getOwnedFinancialAsset(), historyAmount.getAmount()));
				historyAmountRepository.save(historyAmountCopy);
			}
			else if(historyAmount.getTotal().compareTo(BigDecimal.ZERO) == 0){
				//Updates the totalPrice of the asset because the exchange was not working at the time of adding it.
				historyAmount.setTotal(getTotalPriceForOneAsset(historyAmount.getOwnedFinancialAsset(), historyAmount.getAmount()));
			}
			else {
				log.info(String.format("History amount with id '%d' already exist in the date '%s'",
						historyAmount.getId(), historyAmount.getDate()));
			}
		}
	}
}
