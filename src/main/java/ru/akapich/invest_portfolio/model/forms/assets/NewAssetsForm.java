package ru.akapich.invest_portfolio.model.forms.assets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Object that get data to input new asset to database.
 *
 * @author Aleksandr Marakulin
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class NewAssetsForm {

	private String ticker;
	private String type;
	private BigDecimal amount;
}
