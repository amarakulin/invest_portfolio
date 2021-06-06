package ru.akapich.invest_portfolio.service.impl;

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

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByLogin(username);

		if (user == null){
			throw new UsernameNotFoundException("Неправильный Логин/Пароль");
		}
		else{
			Collection<SimpleGrantedAuthority> roles = new HashSet<>();
			roles.add(new SimpleGrantedAuthority(user.getRole()));
			return new org.springframework.security.core.userdetails.User(
					user.getLogin(),
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
		//TODO check if exist user
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		userRepository.save(user);
		System.out.println("Saved user");
	}
}
