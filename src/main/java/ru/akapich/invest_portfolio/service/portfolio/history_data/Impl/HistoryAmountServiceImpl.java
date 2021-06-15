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

/**
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

		String date = dateService.getCurrentDate();
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
}
