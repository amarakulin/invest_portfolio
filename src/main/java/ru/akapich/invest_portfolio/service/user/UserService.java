package ru.akapich.invest_portfolio.service.user;

import ru.akapich.invest_portfolio.model.user.User;

/**
 * Service class for {@link User}
 *
 * @author Aleksandr Marakulin
**/

public interface UserService {

	void save(User user);

	boolean isNameExist(String login);

	boolean isEmailExist(String email);

	User getUserInCurrentSession();
}
