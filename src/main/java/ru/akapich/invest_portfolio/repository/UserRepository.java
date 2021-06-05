package ru.akapich.invest_portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User getUserByLogin(String login);
}
