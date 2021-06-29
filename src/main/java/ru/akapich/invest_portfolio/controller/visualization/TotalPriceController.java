package ru.akapich.invest_portfolio.controller.visualization;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akapich.invest_portfolio.service.portfolio.visualization.TotalPriceService;

import java.math.BigDecimal;

/**
 * Controller to represent TotalPrice on main Page
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
@PropertySource("classpath:message.properties")
public class TotalPriceController {

	@Autowired
	private TotalPriceService totalPriceService;

	@GetMapping("/api/data/totalprice")
	public BigDecimal getTotalPrice(){

		return totalPriceService.getTotalPrice();
	}
}
