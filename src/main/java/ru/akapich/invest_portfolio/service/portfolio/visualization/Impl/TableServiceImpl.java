package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.visualization.FormTable;
import ru.akapich.invest_portfolio.service.portfolio.visualization.TableService;

/**
 * Implementation of {@link TableService} interface
 *
 * @author Aleksandr Marakulin
 **/


@Log4j2
@Service
public class TableServiceImpl implements TableService {
	@Override
	public FormTable getTable() {
		return null;
	}
}
