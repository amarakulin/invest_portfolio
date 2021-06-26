package ru.akapich.invest_portfolio.model.forms.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Object ot get info for new category
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreateForm {

	private String name;
	private List<String> tickers;

}
