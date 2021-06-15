package ru.akapich.invest_portfolio.repository.portfolio.history_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;

/**
 * @author Aleksandr Marakulin
 **/

@Repository
public interface HistoryAmountRepository extends JpaRepository<HistoryAmount, Long> {
}
