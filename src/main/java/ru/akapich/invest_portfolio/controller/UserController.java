package ru.akapich.invest_portfolio.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import java.security.Principal;
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

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		log.info(response.toString());
		return response;
	}

////	@PostMapping(path = "/succeslogin",  consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//	@RequestMapping(path = "/succeslogin", method = RequestMethod.POST)
//	public String succeslogin(LoginForm form, Model model, Principal principal) {
//		log.info("/succeslogin! ");
//		return "succeslogin ";
//	}
//
//	@RequestMapping("/mapping")
//	public String myMethod(Principal principal, ModelMap model){
//		UserDetailsServiceImpl userDetails = (UserDetailsServiceImpl)principal;
//		model.addAttribute("firstName");
//		model.addAttribute("lastName");
//		System.out.println("Mapping");
//		return "Mapping";
//	}
//
//
//	@PostMapping(path = "/api/auth/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//	public String loginPost(@Valid LoginForm form, BindingResult bindingResult, Model model) {
//		User user = null;
//		String errorMessage = "";
//		log.info(String.format("333 loginPost 333 email: %s, password: %s", form.getEmail() , form.getPassword() ));
//		//TODO didn't match password!!!!
//
//		return "LoginPost";
//	}

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponseForm currentUserName(Authentication authentication) {
		log.info(String.format("Front ask for user: %s", authentication.getName()));
		User user = userRepository.getUserByName(authentication.getName());
		return getLoginResponse(user, "");
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

