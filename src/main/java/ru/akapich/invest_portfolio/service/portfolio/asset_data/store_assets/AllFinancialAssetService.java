package ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets;

import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AllFinancialAssetImpl;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface of class {@link AllFinancialAssetImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface AllFinancialAssetService {

	void insertAllAssets(List<Map<String, String>> listAssets) throws IOException;
}
