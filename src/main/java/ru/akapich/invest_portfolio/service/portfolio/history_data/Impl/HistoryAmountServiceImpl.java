package ru.akapich.invest_portfolio.service.portfolio.history_data.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.service.date.DateService;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;

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
	private DateService dateService;

	@Override
	@Transactional
	public void addNewHistoryAmount(OwnedFinancialAsset ownedFinancialAsset, BigDecimal amount) {

		String date = dateService.getCurrentDateAsString();
		log.info(String.format("addNewHistoryAmount: ticker ownedFinancialAsset %s | amount %f | date %s",
				ownedFinancialAsset.getFinancialAssetInUse().getIdAllFinancialAsset().getTicker(),
				amount, date));
		HistoryAmount historyAmount = HistoryAmount.builder()
				.ownedFinancialAsset(ownedFinancialAsset)
				.amount(amount)
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
				historyAmountRepository.save(historyAmountCopy);
			}
			else {
				log.info(String.format("History amount with id '%d' already exist in the date '%s'",
						historyAmount.getId(), historyAmount.getDate()));
			}
		}
	}

	@Override
	public void updateHistoryAmountByOwnedFinancialAsset(OwnedFinancialAsset ownedFinancialAsset) {

	}
}
