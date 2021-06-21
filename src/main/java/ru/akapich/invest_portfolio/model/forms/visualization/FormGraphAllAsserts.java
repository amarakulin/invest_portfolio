package ru.akapich.invest_portfolio.model.forms.visualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Object that represent a data for all asserts in the invest portfolio
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormGraphAllAsserts {

	private String ticker;
	private List<List<BigDecimal>> values;
}
