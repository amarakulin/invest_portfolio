package ru.akapich.invest_portfolio.service.portfolio.asset_data.info_assets.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.Currency;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.info_assets.CurrencyRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.info_assets.CurrencyService;

/**
 * Implementation of {@link CurrencyService} interface.
 *
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	@Transactional
	public Currency findOrAddCurrencyByName(String name) {
		Currency currency;

		currency = currencyRepository.findCurrencyByName(name);
		if (currency == null){
			currency = Currency.builder().name(name).build();
			currencyRepository.save(currency);
			log.info(String.format("[+] Add new currency - '%s'", name));
		}
		return currency;
	}
}