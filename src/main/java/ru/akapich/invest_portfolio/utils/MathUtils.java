package ru.akapich.invest_portfolio.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * External mathematics methods
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class MathUtils {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public static BigDecimal getPercent(BigDecimal total, BigDecimal value){
		BigDecimal percent;
		if (total.compareTo(BigDecimal.ZERO) != 0) {
			percent = divideBigDecimalWithFourPrecisionUp(value, total).multiply(ONE_HUNDRED);
		}
		else{
			percent = BigDecimal.ZERO;
		}

		return percent;
	}

	public static BigDecimal divideBigDecimalWithFourPrecisionUp(BigDecimal numerator, BigDecimal denominator){
		return numerator.divide(denominator, 4, RoundingMode.UP);
	}

	public static BigDecimal divideBigDecimalWithTwoPrecisionHalf(BigDecimal numerator, BigDecimal denominator){
		return numerator.divide(denominator, 2, RoundingMode.HALF_EVEN);
	}

	public static boolean isIntegerValue(BigDecimal bigDecimal){
		return  (bigDecimal.scale() <= 0
				|| bigDecimal.signum() == 0
				|| bigDecimal.stripTrailingZeros().scale() <= 0);
	}
}
