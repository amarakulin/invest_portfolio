package ru.akapich.invest_portfolio.service.portfolio.asset_data.info;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.info.TypeAsset;

/**
 * Service class of {@link TypeAsset}
 *
 * @author Aleksandr Marakulin
 **/

public interface TypeAssetService {

	TypeAsset findOrAddExchangeByName(String name);
}
