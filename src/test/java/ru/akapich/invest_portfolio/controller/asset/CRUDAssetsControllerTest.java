package ru.akapich.invest_portfolio.controller.asset;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.akapich.invest_portfolio.InvestPortfolioApplication;
import ru.akapich.invest_portfolio.controller.assets.CRUDAssetsController;
import ru.akapich.invest_portfolio.parser.info_assets.america.ParseInfoAmericanStock;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.AllFinancialAssetService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Test class for {@link CRUDAssetsController}
 *
 * @author Aleksandr Marakulin
 **/

@AutoConfigureMockMvc
@SpringBootTest(classes = {
		InvestPortfolioApplication.class,
		CRUDAssetsController.class})
@ActiveProfiles("application.properties")
public class CRUDAssetsControllerTest {

	@Autowired
	private AllFinancialAssetService allFinancialAssetService;

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Autowired
	private ParseInfoAmericanStock parseInfoAmericanStock;

	@Autowired
	private ObjectMapper objectMapper;


	@BeforeEach
	void initDatabase() throws IOException {

		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String > map1 = Map.of("symbol", "A1",
				"name", "name1",
				"currency", "USD",
				"exchange", "NYSE",
				"type", "Stock");
		Map<String, String > map2 = Map.of("symbol", "A2",
				"name", "name2",
				"currency", "USD",
				"exchange", "NYSE",
				"type", "Stock");
		list.add(map1);
		list.add(map2);
		allFinancialAssetService.insertAllAssets(list);
	}

	@Test
	public void someTest(){
		System.out.println("Done!");
	}
}
