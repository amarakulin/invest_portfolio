package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.controller.assets.UtilsAssetsController;
import ru.akapich.invest_portfolio.model.forms.assets.MatchAssetForm;

import java.util.List;

/**
 * Interface for {@link UtilsAssetsController}
 *
 * @author Aleksandr Marakulin
 **/

public interface UtilsAssetsService {

	List<MatchAssetForm> getListMatchTickers(String tickerToMatch);

	List<String> getListAllTickersOfUser();
}
