package ru.akapich.invest_portfolio.service.portfolio.visualization;

import ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.TotalPriceServiceImpl;

import java.math.BigDecimal;

/**
 * Interface of {@link TotalPriceServiceImpl}
 *
 * @author Aleksandr Marakulin
 **/

public interface TotalPriceService {

	BigDecimal getTotalPrice();
}
