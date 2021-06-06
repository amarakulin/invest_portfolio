package ru.akapich.invest_portfolio.validator;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Controller to create a answer, if params not valid.
 *
 * @author Aleksandr Marakulin
 **/
@Controller
public class ValidatorController implements ErrorController {

	public static Map<String, String> getErrors(BindingResult bindingResult) {
		Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
				fieldError -> fieldError.getField() + "Error",
				FieldError::getDefaultMessage
		);

		return bindingResult.getFieldErrors().stream().collect(collector);
	}
}
