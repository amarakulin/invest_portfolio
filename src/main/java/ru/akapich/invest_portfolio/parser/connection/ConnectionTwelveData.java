package ru.akapich.invest_portfolio.parser.connection;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Component
public class ConnectionTwelveData {

	public URL getRequestURL(String stringURL) throws IOException {
		try {
			URL requestURL = new URL(stringURL);
			HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();

			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", "twelve_java/1.0");
			connection.connect();

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Request failed. Error: " + connection.getResponseMessage());
			}
			return requestURL;
		}
		catch (IOException e){
			log.error("[-] Got a IOException while connecting to TwelveData");
			throw new IOException();
		}
	}
}
