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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Class to get a price of {@link FinancialAssetInUse}
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
		System.out.println(String.format("Try find a status: %s", allAsset.findValues("status")));//TODO test if work just change it to one if
		if (!allAsset.has("status")) {
			for (Iterator<String> it = allAsset.fieldNames(); it.hasNext(); ) {
				String key = it.next();//FIXME check !allAsset.get(key).has("status")
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
					log.info("[-] Get an error in getAllPriceAmericanAssets. Couldn't get a data from 'twelvedata.com' with text: ");
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

	//TODO Set g IOException
	public Map<String, BigDecimal> getAllPriceAmericanAssets(String exchange) throws IOException, InterruptedException {
		Map<String, BigDecimal> parsedOutputWithTickerAndPrice = new HashMap<>();
		String requestUrl;
		String stringTickers;
		LinkedList<String> listTickersForURL;
		StringBuilder responseData;
		LinkedList<String> listTickers;
		int timesForRequest;
		int i = 0;

		System.out.println("Start getAllPriceAmericanAssets");
		listTickers = historyPriceService.getListTickersToUpdateByExchange(exchange);
		System.out.println(String.format("Get list of tickers: %s", listTickers));
		timesForRequest = listTickers.size() / LIMIT_TICKERS_PER_ONE_REQUEST;
		//TODO handle situation then assets in use more the  LIMIT_TICKERS_PER_ONE_REQUEST tickers * TIME_TO_WAIT minutes !!!
		do {
			listTickersForURL = listTickers.stream().limit(LIMIT_TICKERS_PER_ONE_REQUEST).collect(Collectors.toCollection(LinkedList::new));
			listTickers.removeAll(listTickersForURL);
			System.out.println(String.format("Len tickers: %d", listTickersForURL.size()));
			stringTickers = String.join(",", listTickersForURL);
			requestUrl = String.format(URL_FORM, stringTickers, API_KEY);
			System.out.println(String.format("The list for url %s", listTickersForURL));
			System.out.println(String.format("The string for url %s", requestUrl));
			URL requestURL = connectionTwelveData.getRequestURL(requestUrl);
			responseData = UtilsParser.getResponseData(requestURL);
			parsedOutputWithTickerAndPrice.putAll(parseData(responseData, listTickersForURL));
			i++;
			if (i <= timesForRequest) {
				TimeUnit.MILLISECONDS.sleep(TIME_TO_WAIT);
			}
		} while (i <= timesForRequest);
		System.out.println(parsedOutputWithTickerAndPrice);
		return parsedOutputWithTickerAndPrice;
	}
}
