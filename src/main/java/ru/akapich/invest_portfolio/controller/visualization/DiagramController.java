package ru.akapich.invest_portfolio.controller.visualization;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.akapich.invest_portfolio.model.forms.visualization.DiagramResponseForm;
import ru.akapich.invest_portfolio.service.portfolio.visualization.DiagramService;

import java.util.List;

/**
 * Controller for {@link DiagramResponseForm} to represent Diagram on main Page
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
@PropertySource("classpath:message.properties")
public class DiagramController {

	@Autowired
	private DiagramService diagramService;

	@GetMapping("/api/data/diagram")
	public List<DiagramResponseForm> diagram(){
		List<DiagramResponseForm> listDiagramResponseForm = diagramService.getListDiagramForms();
		return listDiagramResponseForm;
	}
}
