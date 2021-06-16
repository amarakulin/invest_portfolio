package ru.akapich.invest_portfolio.controller.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.login.LoginResponseForm;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationFrom;
import ru.akapich.invest_portfolio.model.user.User;
import ru.akapich.invest_portfolio.service.user.impl.UserDetailsServiceImpl;
import ru.akapich.invest_portfolio.utils.UtilsUser;
import ru.akapich.invest_portfolio.validator.ValidatorController;
import javax.validation.Valid;

/**
 * Controller for {@link User}'s pages.
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PropertySource("classpath:message.properties")
public class UserController {

	@Autowired
	private UtilsUser utilsUser;

	@Autowired
	Environment env;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

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

	@RequestMapping(value = "/api/username", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponseForm currentUserName() {
		String errorMessage = "";
		User user = utilsUser.getUserInCurrentSession();

		if (user == null) {
			errorMessage = env.getProperty("valid.wrong.email_password");
			if (errorMessage == null){
				errorMessage = "Неизвестная ошибка";
			}
			log.info("[-] (Get [/username]) - doesn't exist");
		}
		else{
			log.info(String.format("[+] Front ask for user: %s", user.getName()));
		}
		return getLoginResponse(user, errorMessage);
	}


	@CrossOrigin(origins = "http://localhost:3000/signup")
	@PostMapping("/api/auth/signup")
	public LoginResponseForm registration(@Valid @RequestBody RegistrationFrom form,
											BindingResult bindingResult, Model model) {
		User user = null;
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
			user = User.builder().
					name(form.getName()).
					email(form.getEmail()).
					password(form.getPassword()).
					role("{role.user}").
					enable(true).
					build();
			userDetailsService.save(user);

			log.info(String.format("[+] New User '%s' successfully register with email '%s'.",
								user.getName(), user.getEmail()));
		}

		return getLoginResponse(user, errorMessage);
	}
}

