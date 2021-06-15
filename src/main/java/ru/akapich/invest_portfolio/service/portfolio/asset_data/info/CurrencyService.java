package ru.akapich.invest_portfolio.service.portfolio.asset_data.info;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.info_assets.Currency;

/**
 * Service class of {@link Currency}
 *
 * @author Aleksandr Marakulin
 **/

public interface CurrencyService {

	Currency findOrAddCurrencyByName(String name);
}
