package ru.akapich.invest_portfolio.parser.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * External methods to parse data from a website
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Component
public class UtilsParser {

	public static StringBuilder getResponseData(URL requestURL) throws IOException {
		StringBuilder responseData = new StringBuilder();
		try {
			Scanner scanner = new Scanner(requestURL.openStream());
			while (scanner.hasNextLine()) {
				responseData.append(scanner.nextLine());
			}
			return responseData;
		}
		catch (IOException e){
			log.error("[-] Got a IOException while connecting to TwelveData");
			throw new IOException();
		}
	}
}
