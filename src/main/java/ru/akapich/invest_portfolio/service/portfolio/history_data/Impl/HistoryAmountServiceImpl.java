package ru.akapich.invest_portfolio.service.portfolio.history_data.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryPrice;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryPriceRepository;
import ru.akapich.invest_portfolio.service.date.DateService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;

import javax.persistence.NonUniqueResultException;
import java.math.BigDecimal;
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
	private DateService dateService;

	@Override
	public BigDecimal getTotalPriceForOneAsset(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount) {
		HistoryPrice historyPriceForOneAsset;
		BigDecimal priceForOneAsset = BigDecimal.ZERO;

		FinancialAssetInUse financialAssetInUse = ownedFinancialAsset.getFinancialAssetInUse();
		log.info(String.format("[+] getTotalPriceForOneAsset ticker '%s'", financialAssetInUse.getIdAllFinancialAsset().getTicker()));
//		try {
			historyPriceForOneAsset = historyPriceRepository.findByIdFinancialAssetInUse(financialAssetInUse);
			log.info(String.format("[+] getTotalPriceForOneAsset history_price '%s'", historyPriceForOneAsset));
//		}
//		catch (NonUniqueResultException e){
//			historyPriceForOneAsset = null;
//			log.warn("[-] Several price value for one date in table HistoryPrice");
//			log.warn(String.format("[-] Traceback '%s'", e.getMessage()));
//		}
		if (historyPriceForOneAsset != null){
			priceForOneAsset = historyPriceForOneAsset.getPrice();
		}
		return amount.multiply(priceForOneAsset);
	}

	@Override
	@Transactional
	public void addNewHistoryAmount(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount) {

		String date = dateService.getCurrentDateAsString();//FIXME Handle if date is not a work time of exchange

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

		List<OwnedFinancialAsset> uniqueOwnedAssets = historyAmountRepository.findAllUniqueOwnedAssets();
		System.out.println("In getLastAmountForEachUniqueOwnedAsset()");
		for (OwnedFinancialAsset asset : uniqueOwnedAssets){
			if (!setLastAmount.add(historyAmountRepository.lastAmountByOwnedFinancialAsset(asset))){
				log.warn(String.format("Repeat value of OwnedFinancialAsset '%d' in unique order",  asset.getId()));
			}
		}
		return setLastAmount;
	}

	@Override
	@Transactional
	public void updateAllHistoryAmount() throws CloneNotSupportedException {
		HistoryAmount historyAmountCopy;
		String currentDate  = dateService.getCurrentDateAsString();

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

	@Override
	public void updateHistoryAmountByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset) {
		//FIXME Handle if date is not a work time of exchange
	}
}
