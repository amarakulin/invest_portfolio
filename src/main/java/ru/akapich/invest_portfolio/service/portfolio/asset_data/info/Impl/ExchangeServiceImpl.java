package ru.akapich.invest_portfolio.service.portfolio.asset_data.info.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.Exchange;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.info.ExchangeRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.info.ExchangeService;

/**
 * Implementation of {@link ExchangeService} interface.
 *
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private ExchangeRepository exchangeRepository;

	@Override
	@Transactional
	public Exchange findOrAddExchangeByName(String name) {
		Exchange exchange;

		exchange = exchangeRepository.findExchangeByName(name);
		if (exchange == null){
			exchange = Exchange.builder().name(name).build();
			exchangeRepository.save(exchange);
			log.info(String.format("Add new exchange - '%s'", name));
		}
		return exchange;
	}
}
