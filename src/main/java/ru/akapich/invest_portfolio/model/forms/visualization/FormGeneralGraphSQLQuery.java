package ru.akapich.invest_portfolio.model.forms.visualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormGeneralGraphSQLQuery {

	private LocalDateTime date;
	private BigDecimal total;
	private InvestPortfolio investPortfolio;
}
