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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security Configuration.
 * This class сonfigures authorization and navigate user after login
 *
 * @author Aleksandr Marakulin
 **/

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

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

		/* Let send requests for server */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.cors(withDefaults())
				.csrf().disable()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginProcessingUrl("/api/login").permitAll().defaultSuccessUrl("/home")//
				.and()
				.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
	}
}
//				.csrf().disable()
//				.authorizeRequests()
//				.anyRequest().authenticated()
//				.and()
//				.formLogin().loginPage("/api/login").permitAll().defaultSuccessUrl("/home")
//				.and()
//				.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();

//		http
//				.csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/home").authenticated()
//				.antMatchers("/**").permitAll()
//				.and()
//				.formLogin().permitAll().defaultSuccessUrl("/home")
//				.and()
//				.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();


