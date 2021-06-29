package ru.akapich.invest_portfolio.configuration.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JavaBean object that handle success logout.
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException{
		httpServletResponse.getWriter().append("logout");
		httpServletResponse.setStatus(200);
	}
}