package ru.akapich.invest_portfolio.model.forms.visualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Object that send a response with data which represent a one asset in diagram.
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagramResponseForm {

	private String name;
	private String ticker;
	private BigDecimal percent;
	private BigDecimal value;
	private String color;
}
