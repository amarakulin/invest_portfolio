package ru.akapich.invest_portfolio.repository.portfolio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.Statistic;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
