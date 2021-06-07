package ru.akapich.invest_portfolio.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.Forms.RegistrationFrom;
import ru.akapich.invest_portfolio.model.User;
import ru.akapich.invest_portfolio.service.UserService;
import ru.akapich.invest_portfolio.validator.ValidatorController;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
public class UserController {

	@Autowired
	private UserService userDetailsService;

	@GetMapping("/home")
	public String home() {
		log.info("/home! ");
		return "success user IN ";
	}

	@PostMapping("/registration")
	public String registration(@Valid @RequestBody RegistrationFrom form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.mergeAttributes(ValidatorController.getErrors(bindingResult));

			//Logging
			log.info(String.format("[-] User '%s' try to register and didn't pass validation",
									form.getLogin()) );
			Set<Map.Entry<String, Object>> map = model.asMap().entrySet();
			map.stream().filter(entry -> entry.getKey().contains("Error")).forEach(entry -> log.info(String.format(
					"[-] Error type: %s | Error message: %s",
					entry.getKey(),
					entry.getValue())));

			return "User didn't pass validation";
		}
		else {
			User user = User.builder().
					login(form.getLogin()).
					email(form.getEmail()).
					password(form.getPassword()).
					role("role.user").
					enable(true).
					build();

			userDetailsService.save(user);

			log.info(String.format("[+] New User '%s' successfully register with email '%s'.",
								user.getLogin(), user.getEmail()));

			return "Registration success ";
		}
	}
}

