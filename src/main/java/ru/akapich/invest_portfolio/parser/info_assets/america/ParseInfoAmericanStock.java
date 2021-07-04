package ru.akapich.invest_portfolio.parser.info_assets.america;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.parser.connection.ConnectionTwelveData;
import ru.akapich.invest_portfolio.parser.utils.UtilsParser;

/**
 * JavaBean object that parse a info data of all {@link AllFinancialAsset}
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class ParseInfoAmericanStock {

	@Autowired
	private ConnectionTwelveData connectionTwelveData;

	//TODO encode !!
	private static final String API_KEY = "some api key";

	private static final String URL_FORM = "https://api.twelvedata.com/stocks?exchange=%s&apikey=%s";



	public List<Map<String, String>> getAllStocksByAmericanExchange(String exchange) throws IOException {
		String stringURL = String.format(URL_FORM, exchange, API_KEY );

		URL requestURL = connectionTwelveData.getRequestURL(stringURL);
		StringBuilder responseData = UtilsParser.getResponseData(requestURL);
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> listAssets = new ArrayList<>();

		JsonNode allAsset = mapper.readTree(responseData.toString());
		for (JsonNode asset : allAsset){
			for (JsonNode assetData : asset){
				listAssets.add(
					Arrays.stream(assetData.toString()
							.replace("{", "")
							.replace("}", "")
							.replace("\",\"", "=")
							.replace("\"", "")
							.split("="))
							.map(s -> s.split(":", 2))
							.collect(Collectors.toMap(s -> s[0], s -> s[1]))
				);
			}
		}
		return listAssets;
	}
}
