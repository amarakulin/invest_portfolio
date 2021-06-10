//package ru.akapich.invest_portfolio.configuration.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class CustomBeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
////	@Autowired
////	private CustomerServices customerService;
//
//	@Autowired
//	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//		super.setAuthenticationManager(authenticationManager);
//	}
//
//	@Autowired
//	@Override
//	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
//		super.setAuthenticationFailureHandler(failureHandler);
//	}
//
//	@Autowired
//	@Override
//	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
//		super.setAuthenticationSuccessHandler(successHandler);
//	}
//
//	public CustomBeforeAuthenticationFilter() {
//		setUsernameParameter("email");
//		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/auth/login", "POST"));
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//
//		String email = request.getParameter("email");
//		System.out.println("email: " + email);
//		System.out.println("request.getPathInfo(): " + request.getPathInfo());
//		System.out.println("request.getAuthType(): " + request.getAuthType());
//		System.out.println("request.getMethod(): " + request.getMethod());
//		System.out.println("request.getQueryString(): " + request.getQueryString());
//		System.out.println("request.getRemoteUser(): " + request.getRemoteUser());
//		System.out.println("request.getRequestURI(): " + request.getRequestURI());
//		System.out.println("request.getServletPath(): " + request.getServletPath());
//
//		System.out.println("===============");
//		System.out.println("response.toString(): " + response.toString());
//		System.out.println("response.getCharacterEncoding(): " + response.getCharacterEncoding());
//		System.out.println("response.getContentType(): " + response.getContentType());
//		System.out.println("response.getStatus(): " + response.getStatus());
//		System.out.println("response.getHeaderNames(): " + response.getHeaderNames());
//		System.out.println("response.getLocale().toString(): " + response.getLocale().toString());
//
//		return super.attemptAuthentication(request, response);
//	}
//}
