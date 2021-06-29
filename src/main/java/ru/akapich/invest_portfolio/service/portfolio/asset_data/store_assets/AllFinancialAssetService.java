package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface for class {@link AllFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

public interface AllFinancialAssetService {

	void insertAllAssets(List<Map<String, String>> listAssets) throws IOException;
}
