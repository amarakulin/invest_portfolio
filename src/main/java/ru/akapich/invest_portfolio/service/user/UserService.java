package ru.akapich.invest_portfolio.service.user;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.akapich.invest_portfolio.model.forms.assets.BaseResponseForm;
import ru.akapich.invest_portfolio.model.forms.login.RegistrationForm;
import ru.akapich.invest_portfolio.model.user.User;

/**
 * Service class for {@link User}
 *
 * @author Aleksandr Marakulin
**/

public interface UserService {

	void saveNewUser(RegistrationForm form);

	boolean isNameExist(String login);

	boolean isEmailExist(String email);

	User getUserInCurrentSession();

	BaseResponseForm getResponseRegistration(BindingResult bindingResult, Model model);


}
