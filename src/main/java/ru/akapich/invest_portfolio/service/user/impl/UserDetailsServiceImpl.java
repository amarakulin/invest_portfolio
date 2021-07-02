package ru.akapich.invest_portfolio.service.user.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.user.User;
import ru.akapich.invest_portfolio.repository.user.UserRepository;

import java.util.Collection;
import java.util.HashSet;

/**
 * Implementation of {@link UserDetailsService} interfaces.
 *
 * @author Aleksandr Marakulin
 **/


@Log4j2
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(email);
		if (user != null){

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
}
