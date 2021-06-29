package ru.akapich.invest_portfolio.model.forms.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;

import java.time.LocalDateTime;

/**
 * Object to get purchase date form db {@link HistoryAmountRepository}
 *
 * @author Aleksandr Marakulin
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormPurchaseDate {

	private OwnedFinancialAsset ownedFinancialAsset;
	private LocalDateTime date;
	private InvestPortfolio investPortfolio;
}
