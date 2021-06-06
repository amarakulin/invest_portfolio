package ru.akapich.invest_portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.Forms.RegistrationFrom;
import ru.akapich.invest_portfolio.model.User;
import ru.akapich.invest_portfolio.service.UserService;


/**
 * Controller for {@link User}'s pages.
 *
 * @author Aleksandr Marakulin
 **/

@RestController
public class UserController {

	@Autowired//TODO rewrite
	private UserService userDetailsService;

	@GetMapping("/home")
	public String home() {

		System.out.println("/home! ");
		return "success user IN ";
	}

	@PostMapping("/registration")
	public String registration(@RequestBody RegistrationFrom form, Model model){
		//TODO validation
		User user = User.builder().
				login(form.getLogin()).
				email(form.getEmail()).
				password(form.getPassword()).
				role("Fucking_Role").
				enable(true).
				build();
		System.out.println(user.getId());
		userDetailsService.save(user);
		return "Registration success ";
	}

}
