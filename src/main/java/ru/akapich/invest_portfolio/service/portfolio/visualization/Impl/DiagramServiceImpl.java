package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.service.portfolio.history_data.HistoryAmountService;
import ru.akapich.invest_portfolio.service.user.UserService;
import ru.akapich.invest_portfolio.utils.MathUtils;
import ru.akapich.invest_portfolio.model.forms.visualization.DiagramResponseForm;
import ru.akapich.invest_portfolio.model.portfolio.asset_data.store_assets.FinancialAssetInUse;
import ru.akapich.invest_portfolio.model.portfolio.history_data.HistoryAmount;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.OwnedFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.visualization.DiagramService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link DiagramService} interface
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@Service
public class DiagramServiceImpl implements DiagramService{

	@Autowired
	private UserService userService;

	@Autowired
	private OwnedFinancialAssetRepository ownedFinancialAssetRepository;

	@Autowired
	private HistoryAmountRepository historyAmountRepository;

	@Autowired
	private HistoryAmountService historyAmountService;

	@Override
	public List<DiagramResponseForm> getListDiagramForms() {
		//TODO Refactor!!!!!!!!!!!!!!!!!!!!!!!!!!!
		List<DiagramResponseForm> listDiagramResponseForm = new ArrayList<>();
		FinancialAssetInUse financialAssetInUse;


		if (userService.getUserInCurrentSession() == null){
			System.out.println("Here!");
			return listDiagramResponseForm;
		}
		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		log.info(String.format("[+] Collecting graph data for user with investPortfolio: %d", investPortfolio.getId()));
		LocalDateTime date = historyAmountRepository.getLastTimeUpdateAssetsByInvestPortfolio(investPortfolio);
		System.out.println(String.format("Last time update diagram: %s", date));
		if (date == null){
			System.out.println(String.format("Data Diagram: %s", listDiagramResponseForm));
			return listDiagramResponseForm;
		}
		log.info(String.format("[+] Creating diagram for user with investPortfolio '%d'", investPortfolio.getId()));

		List<HistoryAmount> setOfAllAssets = historyAmountService.getAllByDateAndInvestPortfolioDependsCategory(investPortfolio, date);
		BigDecimal totalPriceInvestPortfolio = historyAmountService.getTotalPriceByDateAndInvestPortfolioDependsCategory(investPortfolio, date);

		log.info(String.format("[+] Total price of the investPortfolio '%f'", totalPriceInvestPortfolio));
		for (HistoryAmount asset : setOfAllAssets){
			System.out.println(asset.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker());
			financialAssetInUse = ownedFinancialAssetRepository.findFinancialAssetInUseByOwnedFinancialAsset(
												asset.getOwnedFinancialAsset());
			if (asset.getAmount().compareTo(BigDecimal.ZERO) != 0) {
				listDiagramResponseForm.add(
						DiagramResponseForm.builder()
								.name(financialAssetInUse.getIdAllFinancialAsset().getName())
								.ticker(financialAssetInUse.getIdAllFinancialAsset().getTicker())
								.value(asset.getTotal())
								.percent(MathUtils.getPercent(totalPriceInvestPortfolio, asset.getTotal()))
								.color(financialAssetInUse.getColor())
								.build()
				);
			}
		}
		log.info(String.format("[+] Finish collect DIAGRAM for user with investPortfolio '%d'", investPortfolio.getId()));
		System.out.println(String.format("Data Diagram: %s", listDiagramResponseForm));
		return listDiagramResponseForm;
	}
}
