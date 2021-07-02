package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.forms.visualization.BodyTable;
import ru.akapich.invest_portfolio.model.forms.visualization.FormTable;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.AllFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.OwnedFinancialAsset;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.OwnedFinancialAssetService;
import ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.enums.HeadersData;
import ru.akapich.invest_portfolio.service.portfolio.visualization.Impl.enums.OrderData;
import ru.akapich.invest_portfolio.service.portfolio.visualization.TableService;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.utils.MathUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Implementation of {@link TableService} interface
 *
 * @author Aleksandr Marakulin
 **/


@Log4j2
@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private UserService userService;

	@Autowired
	private OwnedFinancialAssetService ownedFinancialAssetService;

	@Autowired
	private HistoryAmountRepository historyAmountRepository;

	@Override
	public Map<String, String> getHeader() {
		return HeadersData.getHeaderData();
	}

	@Override
	public List<BodyTable> getBodyByInvestPortfolio(InvestPortfolio investPortfolio) {
		List<BodyTable> body = new ArrayList<>();
		AllFinancialAsset tmpInfoAsset;
		HistoryAmount tmpHistoryAmount;

		LinkedList<OwnedFinancialAsset> allOwnedFinancialAssets = ownedFinancialAssetService.getAllOwnedAssetByInvestPortfolioDependsCategory(investPortfolio);

		if (allOwnedFinancialAssets.size() != 0){
			for (OwnedFinancialAsset asset : allOwnedFinancialAssets){
				tmpInfoAsset = asset.getFinancialAssetInUse().getIdAllFinancialAsset();
				tmpHistoryAmount = historyAmountRepository.lastAmountByOwnedFinancialAsset(asset);
				if (tmpHistoryAmount != null && tmpHistoryAmount.getAmount().compareTo(BigDecimal.ZERO) != 0) {
					body.add(BodyTable.builder()
							.name(tmpInfoAsset.getName())
							.ticker(tmpInfoAsset.getTicker())
							.type(tmpInfoAsset.getIdTypeAsset().getName())
							.exchange(tmpInfoAsset.getIdExchange().getName())
							.price(MathUtils.divideBigDecimalWithTwoPrecisionHalf(tmpHistoryAmount.getTotal(), tmpHistoryAmount.getAmount()))
							.amount(tmpHistoryAmount.getAmount())
							.total(tmpHistoryAmount.getTotal())
							.build());
				}
			}
		}
		return body;
	}

	@Override
	public LinkedList<String> getOrder() {
		return OrderData.getOrderData();
	}

	@Override
	public FormTable getTable() {

		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();

		log.info(String.format("[+] Table has started to be collected for user with investPortfolioId: %d", investPortfolio.getId()));
		FormTable formTable = FormTable.builder()
				.header(getHeader())
				.body(getBodyByInvestPortfolio(investPortfolio))
				.order(getOrder())
				.build();

		return formTable;
	}
}
