package ru.akapich.invest_portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.Forms.RegistrationFrom;
import ru.akapich.invest_portfolio.model.User;
import ru.akapich.invest_portfolio.service.UserService;

import javax.validation.Valid;


/**
 * Controller for {@link User}'s pages.
 *
 * @author Aleksandr Marakulin
 **/

@RestController
public class UserController {

	@Autowired
	private UserService userDetailsService;

	@GetMapping("/home")
	public String home() {

		System.out.println("/home! ");
		return "success user IN ";
	}

	@PostMapping("/registration")
	public String registration(@Valid @RequestBody RegistrationFrom form, BindingResult bindingResult, Model model){
		//TODO validation
		if (bindingResult.hasErrors()){
//			model.mergeAttributes(form.getErrors(bindingResult));
		}
		User user = User.builder().
				login(form.getLogin()).
				email(form.getEmail()).
				password(form.getPassword()).
				role("Fucking_Role").
				enable(true).
				build();

		userDetailsService.save(user);

		return "Registration success ";
	}

}
