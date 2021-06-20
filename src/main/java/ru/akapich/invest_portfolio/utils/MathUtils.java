package ru.akapich.invest_portfolio.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Aleksandr Marakulin
 **/

@Component
public class MathUtils {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public static BigDecimal getPercent(BigDecimal total, BigDecimal value){
		if (total.compareTo(BigDecimal.ZERO) != 0) {
			return value.multiply(ONE_HUNDRED).divide(total, 2, RoundingMode.CEILING);
		}
		return BigDecimal.ZERO;
//		return total.multiply(value).divide(ONE_HUNDRED, 2, RoundingMode.CEILING);
	}
}
