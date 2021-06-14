package ru.akapich.invest_portfolio.service.portfolio.asset_data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksandr Marakulin
 **/

public interface AllFinancialAssetService {

	void insertAllAssets(List<Map<String, String>> listAssets) throws IOException;
}
