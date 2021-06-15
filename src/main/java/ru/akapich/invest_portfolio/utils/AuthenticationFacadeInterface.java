package ru.akapich.invest_portfolio.utils;

import org.springframework.security.core.Authentication;

/**
 * Interface to get authentication in current session
 *
 * @author Aleksandr Marakulin
 **/

public interface AuthenticationFacadeInterface {
	Authentication getAuthentication();
}
