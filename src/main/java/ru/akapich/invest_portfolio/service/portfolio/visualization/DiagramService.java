package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.DiagramResponseForm;
import ru.akapich.invest_portfolio.controller.visualization.DiagramController;
import java.util.List;

/**
 * Interface of {@link DiagramController}
 *
 * @author Aleksandr Marakulin
 **/

public interface DiagramService {

	List<DiagramResponseForm> getListDiagramForms();
}
