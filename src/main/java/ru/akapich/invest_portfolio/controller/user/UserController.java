package ru.akapich.invest_portfolio.controller.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationFrom;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.user.User;
import ru.akapich.invest_portfolio.service.user.impl.UserDetailsServiceImpl;
import ru.akapich.invest_portfolio.validator.ValidatorController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:3000/*", allowedHeaders = "*", maxAge = 3600)
@PropertySource("classpath:message.properties")
public class UserController {

	@Autowired
	private Environment env;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@CrossOrigin(origins = "http://localhost:3000/signup")
	@PostMapping("/api/auth/signup")
	public BaseResponseForm registration(@Valid @RequestBody RegistrationFrom form,
											BindingResult bindingResult, Model model) {
		String errorMessage = "";

		if (bindingResult.hasErrors()) {
			model.mergeAttributes(ValidatorController.getErrors(bindingResult));

			errorMessage = model.asMap().entrySet().stream().
					filter(key -> key.getKey().contains("Error")).
					findFirst().
					get().
					getValue().toString();//TODO get without isPresent!!!

			log.info(String.format("[-] User '%s' try to register and didn't pass validation. Error message: %s",
					form.getName(), errorMessage));
		}
		else {
			User user = User.builder()
					.name(form.getName())
					.email(form.getEmail())
					.password(form.getPassword())
					.role(env.getProperty("role.user"))
					.investPortfolio(new InvestPortfolio())
					.enable(true)
					.build();
			userDetailsService.save(user);

			log.info(String.format("[+] New User '%s' successfully register with email '%s'.",
								user.getName(), user.getEmail()));
		}
		return BaseResponseForm.builder()
				.error(errorMessage)
				.resultCode(errorMessage.equals("") ? 0 : 1)
				.build();
	}

	@GetMapping("/api/auth/token")
	public Map<String,String> token(HttpSession session) {
		Map<String,String> result = new java.util.HashMap<>(Collections.singletonMap("token", session.getId()));
		System.out.println(String.format("Token: %s",  Collections.singletonMap("token", session.getId())));
		User user = userDetailsService.getUserInCurrentSession();
		result.put("name", user.getName());
		return result;
	}
}

