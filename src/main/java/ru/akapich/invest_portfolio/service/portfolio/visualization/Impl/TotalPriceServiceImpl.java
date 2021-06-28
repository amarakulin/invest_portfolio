package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.service.portfolio.visualization.TotalPriceService;
import ru.akapich.invest_portfolio.service.user.UserService;

import java.math.BigDecimal;

/**
 * Implementation of {@link TotalPriceService}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class TotalPriceServiceImpl implements  TotalPriceService{

	@Autowired
	private HistoryAmountService historyAmountService;

	@Autowired
	private UserService userService;

	@Override
	public BigDecimal getTotalPrice() {

		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		return historyAmountService.getLatestTotalPriceByInvestPortfolioDependsCategory(investPortfolio);
	}
}
