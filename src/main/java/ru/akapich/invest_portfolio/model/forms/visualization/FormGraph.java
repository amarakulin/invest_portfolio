package ru.akapich.invest_portfolio.model.forms.visualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Object that represent a data for all asserts in the invest portfolio
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormGraph {

	//the info with timestamp and price
	private List<List<String>> lines;

	//the info with type of each line
	private Map<String, String> types;

	//the info with name of each line
	private Map<String, String> names;

	//the info with color for each line(except line with timestamp)
	private Map<String, String> color;

	//the info with timestamp bought
	private Map<String, String> purchaseDate;
}
