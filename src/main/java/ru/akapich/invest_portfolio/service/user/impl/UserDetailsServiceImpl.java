package ru.akapich.invest_portfolio.service.user.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.user.User;
import ru.akapich.invest_portfolio.repository.user.UserRepository;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.validator.ValidatorController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;

/**
 * Implementation of {@link UserService} and {@link UserDetailsService} interfaces.
 *
 * @author Aleksandr Marakulin
 **/

@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment env;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		//TODO find another way
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String password = request.getParameter("password");
		log.info(String.format("loadUserByUsername: %s with password: %s", email, password));

		User user = userRepository.getUserByEmail(email);
		if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())){

			Collection<SimpleGrantedAuthority> roles = new HashSet<>();
			roles.add(new SimpleGrantedAuthority(user.getRole()));
			log.info(String.format("[+] User with email: '%s' log in", email));
			return new org.springframework.security.core.userdetails.User(
					user.getName(),
					user.getPassword(),
					user.isEnable(),
					true,
					true,
					true,
					roles);
		}
		else{
			log.info(String.format("[-] User with email: '%s' can't log in", email));
			throw new UsernameNotFoundException("Неправильный Логин/Пароль");
		}
	}

	@Override
	public void saveNewUser(RegistrationForm form){
		User user = User.builder()
				.name(form.getName())
				.email(form.getEmail())
				.password(bCryptPasswordEncoder.encode(form.getPassword()))
				.role(env.getProperty("role.user"))
				.investPortfolio(new InvestPortfolio())
				.enable(true)
				.build();
		userRepository.save(user);
		log.info(String.format("[+] New User '%s' successfully register with email '%s'.",
				user.getName(), user.getEmail()));
	}

	@Override
	public boolean isNameExist(String name) {
		return userRepository.getUserByName(name) != null;
	}

	@Override
	public boolean isEmailExist(String email) {
		return userRepository.getUserByEmail(email) != null;
	}

	@Override
	public User getUserInCurrentSession() {
		User user = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null){

			user = userRepository.getUserByName(authentication.getName());
			if (user != null) {
				log.info(String.format("[+] Current user: %s", user.getName()));
			}
			else{
				log.info("[-] No user in the current session");
			}
		}
		return user;
	}

	@Override
	public BaseResponseForm getResponseRegistration(BindingResult bindingResult, Model model) {
		String errorMessage = "";

		if (bindingResult.hasErrors()) {
			model.mergeAttributes(ValidatorController.getErrors(bindingResult));

			errorMessage = model.asMap().entrySet().stream().
					filter(key -> key.getKey().contains("Error")).
					findFirst().
					get().
					getValue().toString();//TODO get without isPresent!!!
		}
		return BaseResponseForm.builder()
				.error(errorMessage)
				.resultCode("".equals(errorMessage) ? 0 : 1)
				.build();
	}
}
