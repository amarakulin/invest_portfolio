package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.controller.assets.CRUDAssetsController;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;

import java.util.List;

/**
 * Interface for {@link CRUDAssetsController}
 *
 * @author Aleksandr Marakulin
 **/

public interface CRUDAssetsService {

	BaseResponseForm getAssetsResponseForm(List<NewAssetsForm> listAssetsForm);

}
