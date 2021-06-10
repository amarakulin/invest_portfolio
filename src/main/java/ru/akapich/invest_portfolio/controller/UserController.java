package ru.akapich.invest_portfolio.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.LoginForm;
import ru.akapich.invest_portfolio.model.forms.LoginResponseForm;
import ru.akapich.invest_portfolio.model.forms.RegistrationFrom;
import ru.akapich.invest_portfolio.model.User;
import ru.akapich.invest_portfolio.repository.UserRepository;
import ru.akapich.invest_portfolio.service.UserService;
import ru.akapich.invest_portfolio.service.impl.UserDetailsServiceImpl;
import ru.akapich.invest_portfolio.validator.ValidatorController;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private UserRepository userRepository;


	private LoginResponseForm getLoginResponse(User user, String errorMessage){
		//TODO Refactor
		LoginResponseForm response;
		if (errorMessage.equals("")){
			response = LoginResponseForm.builder().
					error(errorMessage).
					resultCode(0).
					userID(user.getId()).
					email(user.getEmail()).
					name(user.getName()).build();
		}
		else{
			response = LoginResponseForm.builder().
					error(errorMessage).
					resultCode(1).
					userID(null).
					email(null).
					name(null).build();
		}
		return response;
	}

	@GetMapping("/api/auth/login")
	public String loginGet() {
		log.info("/loginGET! ");
		return "Login Get ";
	}

	@PostMapping(path = "/api/auth/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String loginPost(@Valid LoginForm form, BindingResult bindingResult, Model model) {
		User user = null;
		String errorMessage = "";
		log.info(String.format("333 loginPost 333 email: %s, password: %s", form.getEmail() , form.getPassword() ));

		//TODO didn't match password!!!!

		return "LoginPost";
	}

	@CrossOrigin(origins = "http://localhost:3000/signup")
	@PostMapping("/api/auth/signup")
	public LoginResponseForm registration(@Valid @RequestBody RegistrationFrom form, BindingResult bindingResult, Model model) {
		User user = null;
		String errorMessage = "";

		if (bindingResult.hasErrors()) {
			model.mergeAttributes(ValidatorController.getErrors(bindingResult));

			errorMessage = model.asMap().entrySet().stream().
					filter(key -> key.getKey().contains("Error")).
					findFirst().
					get().
					getValue().toString();//TODO get withot isPresent!!!

			log.info(String.format("[-] User '%s' try to register and didn't pass validation. Error message: %s",
					form.getName(), errorMessage));
		}
		else {
			user = User.builder().
					name(form.getName()).
					email(form.getEmail()).
					password(form.getPassword()).
					role("role.user").
					enable(true).
					build();
			userDetailsService.save(user);

			log.info(String.format("[+] New User '%s' successfully register with email '%s'.",
								user.getName(), user.getEmail()));
		}

		return getLoginResponse(user, errorMessage);
	}
}

