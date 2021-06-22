package ru.akapich.invest_portfolio.parser.price_assets.america;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Class to get a price of {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Component
public class ParseAmericanPriceAssets {
	//TODO encode !!
	private static final String API_KEY = "1480cef042784c4ea6dc3cd0975ad6e5";

	@Autowired
	private HistoryPriceService historyPriceService;

	//TODO Set g IOException
	public Map<String, BigDecimal> getAllPriceAmericanAssets(String exchange) throws IOException {
		//TODO refactor
		System.out.println("Start getAllPriceAmericanAssets");
		String stringWithTickers = historyPriceService.stringTickersToUpdateByExchange(exchange);
		System.out.println(String.format("Get string of tickers: %s", stringWithTickers));
//
		String REQUEST_URL = String.format("https://api.twelvedata.com/price?symbol=%s&apikey=%s", stringWithTickers, API_KEY );
//		String REQUEST_URL = String.format("https://api.twelvedata.com/price?symbol=AAPL,MCD&apikey=%s", API_KEY );

		URL requestURL = new URL(REQUEST_URL);
		HttpURLConnection connection = (HttpURLConnection)requestURL.openConnection();
		StringBuilder responseData = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
//		List<Map<String, String>> listAssets = new ArrayList<>();

		Map<String, BigDecimal> mapAssetsPrice = new HashMap<>();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "twelve_java/1.0");
		connection.connect();

		if (connection.getResponseCode() != 200) {
			throw new RuntimeException("Request failed. Error: " + connection.getResponseMessage());
		}

		Scanner scanner = new Scanner(requestURL.openStream());
		while (scanner.hasNextLine()) {
			responseData.append(scanner.nextLine());
		}
		System.out.println(responseData.toString());
		JsonNode allAsset = mapper.readTree(responseData.toString());

		if (stringWithTickers.length() != 0) {
			for (Iterator<String> it = allAsset.fieldNames(); it.hasNext(); ) {
				String key = it.next();
				mapAssetsPrice.put(key, BigDecimal.valueOf(allAsset.get(key).get("price").asDouble()));
			}
		}
		else {
			log.info("[-] Get an error in getAllPriceAmericanAssets. Could't get a data from 'twelvedata.com'. No one tickers!");
		}
		System.out.println("mapAssetsPrice: ");
		mapAssetsPrice.forEach((key, value) -> System.out.println(key + ":" + value));
		return mapAssetsPrice;
	}
}
