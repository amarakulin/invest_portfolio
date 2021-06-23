package ru.akapich.invest_portfolio.model.forms.visualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Object that represent one row in the {@link FormTable}
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BodyTable {

	private String name;
	private String ticker;
	private String type;
	private String exchange;
	private BigDecimal price;
	private BigDecimal amount;
	private BigDecimal total;
}