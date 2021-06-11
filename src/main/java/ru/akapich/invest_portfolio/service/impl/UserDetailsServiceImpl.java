package ru.akapich.invest_portfolio.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.akapich.invest_portfolio.model.User;
import ru.akapich.invest_portfolio.repository.UserRepository;
import ru.akapich.invest_portfolio.service.UserService;

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
	public void save(User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public boolean isLoginExist(String login) {
		return userRepository.getUserByName(login) != null;
	}

	@Override
	public boolean isEmailExist(String email) {
		return userRepository.getUserByEmail(email) != null;
	}
}
