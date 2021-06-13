package ru.akapich.invest_portfolio.repository.portfolio.asset_data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.FinancialAssetInUse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * JavaBean object that interaction with Database.
 *
 * @author Aleksandr Marakulin
 **/

@Repository
public interface FinancialAssetRepository extends JpaRepository<FinancialAssetInUse, Integer> {

//	void insertAllAssets(List<String> listAssets);


}
