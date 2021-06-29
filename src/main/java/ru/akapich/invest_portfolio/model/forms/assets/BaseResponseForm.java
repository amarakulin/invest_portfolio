package ru.akapich.invest_portfolio.model.forms.assets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Object that send a response if action user needs validation.
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponseForm {

	private String error;
	private Integer resultCode;
}
