package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.controller.visualization.TotalPriceController;
import java.math.BigDecimal;

/**
 * Interface of {@link TotalPriceController}
 *
 * @author Aleksandr Marakulin
 **/

public interface TotalPriceService {

	BigDecimal getTotalPrice();
}
