package ru.akapich.invest_portfolio.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.user.User;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User getUserByName(String name);
	User getUserByEmail(String email);

}
