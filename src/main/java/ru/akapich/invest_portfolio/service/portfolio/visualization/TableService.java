package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.model.forms.visualization.FormTable;
import ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.TableServiceImpl;

/**
 * Interface of {@link TableServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface TableService {

	FormTable getTable();
}
