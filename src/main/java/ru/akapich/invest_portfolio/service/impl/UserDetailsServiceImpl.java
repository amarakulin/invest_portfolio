package ru.akapich.invest_portfolio.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.User;
import ru.akapich.invest_portfolio.repository.UserRepository;
import ru.akapich.invest_portfolio.service.UserService;

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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByName(username);

		if (user == null){
			log.info(String.format("[-] User '%s' can't log in", username));
			throw new UsernameNotFoundException("Неправильный Логин/Пароль");
		}
		else{
			Collection<SimpleGrantedAuthority> roles = new HashSet<>();
			roles.add(new SimpleGrantedAuthority(user.getRole()));
			log.info(String.format("[+] User '%s' log in", username));
			return new org.springframework.security.core.userdetails.User(
					user.getName(),
					user.getPassword(),
					user.isEnable(),
					true,
					true,
					true,
					roles);
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
