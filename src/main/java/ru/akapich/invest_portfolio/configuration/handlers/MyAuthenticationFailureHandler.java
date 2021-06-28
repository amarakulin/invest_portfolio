package ru.akapich.invest_portfolio.configuration.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JavaBean object that handle failure authentication.
 *
 * @author Aleksandr Marakulin
 **/

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException{
		httpServletResponse.getWriter().append("Authentication failure");
		httpServletResponse.setStatus(401);
	}
}
