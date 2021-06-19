package ru.akapich.invest_portfolio.controller.visualization;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ru.akapich.invest_portfolio.model.forms.visualization.DiagramResponseForm;

/**
 * Controller for {@link DiagramResponseForm}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
@PropertySource("classpath:message.properties")
public class DiagramController {
}
