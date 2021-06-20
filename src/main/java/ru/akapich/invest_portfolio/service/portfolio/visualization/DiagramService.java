package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.DiagramResponseForm;
import ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.DiagramServiceImpl;
import java.util.List;

/**
 * Interface of {@link DiagramServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface DiagramService {

	List<DiagramResponseForm> getListDiagramForms();
}
