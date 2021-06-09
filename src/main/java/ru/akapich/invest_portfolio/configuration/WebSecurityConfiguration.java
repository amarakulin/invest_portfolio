package ru.akapich.invest_portfolio.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security Configuration.
 * This class сonfigures authorization and navigate user after login
 *
 * @author Aleksandr Marakulin
 **/

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public  BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("*");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors(withDefaults())
				.csrf().disable()
				.authorizeRequests().antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginProcessingUrl("/api/auth/login").usernameParameter("email").permitAll().defaultSuccessUrl("/home")//
				.and()
				.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
	}
}



