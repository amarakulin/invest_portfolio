package ru.akapich.invest_portfolio.parser.price_assets.america;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.parser.connection.ConnectionTwelveData;
import ru.akapich.invest_portfolio.parser.utils.UtilsParser;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryPriceService;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Class to get a price of all {@link FinancialAssetInUse}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Component
public class ParseAmericanPriceAssets {

	@Autowired
	private ConnectionTwelveData connectionTwelveData;

	//TODO encode !!
	private static final String API_KEY = "1480cef042784c4ea6dc3cd0975ad6e5";
	private static final int LIMIT_TICKERS_PER_ONE_REQUEST = 7;
	private static final String URL_FORM = "https://api.twelvedata.com/price?symbol=%s&apikey=%s";
	private static final long TIME_TO_WAIT = 60000;

	@Autowired
	private HistoryPriceService historyPriceService;

	private Map<String, BigDecimal> parseData(StringBuilder responseData, LinkedList<String> listTickers) throws JsonProcessingException {
		Map<String, BigDecimal> mapAssetsPrice = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		BigDecimal priceAsset;
		int i = 0;

		JsonNode allAsset = mapper.readTree(responseData.toString());
		if (!allAsset.has("status")) {
			for (Iterator<String> it = allAsset.fieldNames(); it.hasNext(); ) {
				String key = it.next();
				if (!allAsset.get(key).has("status")) {
					if (allAsset.has("price")) {
						priceAsset = BigDecimal.valueOf(allAsset.get("price").asDouble());
					} else {
						priceAsset = BigDecimal.valueOf(allAsset.get(key).get("price").asDouble());
					}
					mapAssetsPrice.put(listTickers.get(i), priceAsset);
					i++;
				}
				else{
					log.info("[-] Get an error in getAllPriceAmericanAssets after get a key of JsonNode. Couldn't get a data from 'twelvedata.com' with text: ");
					log.info(String.format("[-] %s", key));
				}
			}
		}
		else {
			log.info("[-] Get an error in getAllPriceAmericanAssets. Couldn't get a data from 'twelvedata.com' with text: ");
			log.info(String.format("[-] %s", responseData));
		}
		return mapAssetsPrice;
	}

	public Map<String, BigDecimal> getAllPriceAmericanAssets(String exchange) throws IOException, InterruptedException {
		Map<String, BigDecimal> mapWithTickerAndPrice = new HashMap<>();
		String stringUrl;
		LinkedList<String> listTickersForURL;
		StringBuilder responseData;
		LinkedList<String> listTickers;
		int timesForRequest;
		int i = 0;

		listTickers = historyPriceService.getListTickersToUpdateByExchange(exchange);
		timesForRequest = listTickers.size() / LIMIT_TICKERS_PER_ONE_REQUEST;
		//TODO handle situation then assets in use more the  LIMIT_TICKERS_PER_ONE_REQUEST tickers * TIME_TO_WAIT minutes !!!
		do {
			listTickersForURL = listTickers.stream().limit(LIMIT_TICKERS_PER_ONE_REQUEST).collect(Collectors.toCollection(LinkedList::new));
			listTickers.removeAll(listTickersForURL);
			stringUrl = String.format(URL_FORM, String.join(",", listTickersForURL), API_KEY);
			log.info(String.format("[+] Url string for request: %s", stringUrl));
			URL requestURL = connectionTwelveData.getRequestURL(stringUrl);
			responseData = UtilsParser.getResponseData(requestURL);
			mapWithTickerAndPrice.putAll(parseData(responseData, listTickersForURL));
			i++;
			if (i <= timesForRequest) {
				TimeUnit.MILLISECONDS.sleep(TIME_TO_WAIT);
			}
		} while (i <= timesForRequest);
		return mapWithTickerAndPrice;
	}
}
