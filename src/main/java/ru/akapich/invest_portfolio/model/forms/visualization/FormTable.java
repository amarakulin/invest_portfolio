package ru.akapich.invest_portfolio.model.forms.visualization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Object that represent form of table
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormTable {

	//Name of columns
	private Map<String, String> header;

	//All rows with data assets
	private List<BodyTable> body;

	//Order of the name columns
	private List<String> order;
}
