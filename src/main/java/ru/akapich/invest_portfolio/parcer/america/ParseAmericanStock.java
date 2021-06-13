package ru.akapich.invest_portfolio.parcer.america;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Splitter;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 *
 * @author Aleksandr Marakulin
 **/

public class ParseAmericanStock {

	//TODO encode !!
	private static final String API_KEY = "1480cef042784c4ea6dc3cd0975ad6e5";

	public List<Map<String, String>> getAllStocks(String exchange) throws IOException {
		//TODO refactor
		String REQUEST_URL = String.format("https://api.twelvedata.com/stocks?exchange=%s&apikey=%s", exchange, API_KEY );

		URL requestURL = new URL(REQUEST_URL);
		HttpURLConnection connection = (HttpURLConnection)requestURL.openConnection();
		StringBuilder responseData = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> listAssets = new ArrayList<>();

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

		JsonNode allAsset = mapper.readTree(responseData.toString());
		for (JsonNode asset : allAsset){
			for (JsonNode assetData : asset){
				System.out.println(assetData.toString());
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
