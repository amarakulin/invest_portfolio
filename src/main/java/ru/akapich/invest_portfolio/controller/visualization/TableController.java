package ru.akapich.invest_portfolio.controller.visualization;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akapich.invest_portfolio.model.forms.visualization.FormTable;
import ru.akapich.invest_portfolio.service.portfolio.visualization.TableService;

/**
 * Controller that send table with all data about assets in invest portfolio
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
@PropertySource("classpath:message.properties")
public class TableController {

	@Autowired
	private TableService tableService;

	@GetMapping("/api/data/tableassets")
	public FormTable table(){
		return tableService.getTable();
	}

}
