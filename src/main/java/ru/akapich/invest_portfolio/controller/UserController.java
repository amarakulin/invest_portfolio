package ru.akapich.invest_portfolio.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.LoginResponseForm;
import ru.akapich.invest_portfolio.model.forms.RegistrationFrom;
import ru.akapich.invest_portfolio.model.User;
import ru.akapich.invest_portfolio.service.UserService;
import ru.akapich.invest_portfolio.validator.ValidatorController;
import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userDetailsService;


	@GetMapping("/home")
	public String home() {
		log.info("/home! ");
		return "success user IN ";
	}

	@CrossOrigin(origins = "http://localhost:3000/signup")
	@PostMapping("/api/signup")
	public LoginResponseForm registration(@Valid @RequestBody RegistrationFrom form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.mergeAttributes(ValidatorController.getErrors(bindingResult));
			String errorMessage = model.asMap().entrySet().stream().
					filter(key -> key.getKey().contains("Error")).
					findFirst().
					get().
					getValue().toString();//TODO get withot isPresent!!!

			//Logging
			Set<Map.Entry<String, Object>> map = model.asMap().entrySet();
			map.stream().filter(entry -> entry.getKey().contains("Error")).forEach(entry -> log.info(String.format(
					"[-] Error type: %s | Error message: %s",
					entry.getKey(),
					entry.getValue())));

			LoginResponseForm response = LoginResponseForm.builder().
					error(errorMessage).
					resultCode(1).
					userID(null).
					email(null).
					name(null).build();

			log.info(String.format("[-] User '%s' try to register and didn't pass validation",
					form.getName()) );

			return response;
		}
		else {
			User user = User.builder().
					name(form.getName()).
					email(form.getEmail()).
					password(form.getPassword()).
					role("role.user").
					enable(true).
					build();
			userDetailsService.save(user);

			LoginResponseForm response = LoginResponseForm.builder().
					error("").
					resultCode(0).
					userID(user.getId()).
					email(user.getEmail()).
					name(user.getName()).build();

			log.info(String.format("[+] New User '%s' successfully register with email '%s'.",
								user.getName(), user.getEmail()));

			return response;
		}
	}
}

