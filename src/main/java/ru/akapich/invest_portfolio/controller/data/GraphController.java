package ru.akapich.invest_portfolio.controller.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akapich.invest_portfolio.parcer.america.ParseAmericanStock;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PropertySource("classpath:message.properties")
public class GraphController {

	@GetMapping("/api/data/graph")
	public String graph() throws IOException {

		ParseAmericanStock parseAmericanStock = new ParseAmericanStock();
		List<Map<String, String>> listAssets = parseAmericanStock.getAllStocks("NYSE");

		for(var item : listAssets){
			item.forEach((k, v) -> System.out.println(k + ":" + v));
			System.out.println("=================");
		}

		return "Parsed Stock";
	}
}
