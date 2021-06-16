package ru.akapich.invest_portfolio.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.akapich.invest_portfolio.model.user.User;
import ru.akapich.invest_portfolio.repository.user.UserRepository;

/**
 * JavaBean object adds utils functional for {@link User}
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Component
public class UtilsUser implements AuthenticationFacadeInterface{
//TODO move all of this in UserService!!!!!!!!!!!!!!
	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Bean
	public User getUserInCurrentSession(){
		User user = null;

		Authentication authentication = getAuthentication();
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
}
