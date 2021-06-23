package ru.akapich.invest_portfolio.model.forms.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;

import java.time.LocalDateTime;

/**
 * Object to get data form db
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
