package ru.akapich.invest_portfolio.service.portfolio.visualization.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.akapich.invest_portfolio.model.portfolio.InvestPortfolio;
import ru.akapich.invest_portfolio.repository.portfolio.history_data.HistoryAmountRepository;
import ru.akapich.invest_portfolio.service.date.DateService;
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
	private DateService dateService;



	@Override
	public List<DiagramResponseForm> getListDiagramForms() {
		List<DiagramResponseForm> listDiagramResponseForm = new ArrayList<>();
		FinancialAssetInUse financialAssetInUse;
		BigDecimal totalPriceInvestPortfolio;
		LocalDateTime date = dateService.getCurrentTime();

		InvestPortfolio investPortfolio = userService.getUserInCurrentSession().getInvestPortfolio();
		log.info(String.format("[+] Creating diagram for user with investPortfolio '%d'", investPortfolio.getId()));
		Set<HistoryAmount> setOfAllAssets = historyAmountRepository.findAllByOwnedFinancialAsset_InvestPortfolioAndDate(investPortfolio, date);
		try {
			totalPriceInvestPortfolio = historyAmountRepository.getTotalPriceOfInvestPortfolio(investPortfolio, date).setScale(2, RoundingMode.CEILING);
		}
		catch (NullPointerException e){
			totalPriceInvestPortfolio = BigDecimal.ZERO;
			log.info(String.format("[-] InvestPortfolio with id: '%d' Didn't has any assets yet", investPortfolio.getId()));
		}
		log.info(String.format("[+] Total price of the investPortfolio '%f'", totalPriceInvestPortfolio));
		for (HistoryAmount asset : setOfAllAssets){
			System.out.println(asset.getOwnedFinancialAsset().getFinancialAssetInUse().getIdAllFinancialAsset().getTicker());
			financialAssetInUse = ownedFinancialAssetRepository.findFinancialAssetInUseByOwnedFinancialAsset(
												asset.getOwnedFinancialAsset());

			listDiagramResponseForm.add(
				DiagramResponseForm.builder()
						.name(financialAssetInUse.getIdAllFinancialAsset().getName())
						.ticker(financialAssetInUse.getIdAllFinancialAsset().getTicker())
						.value(asset.getTotal())
						.percent(MathUtils.getPercent(totalPriceInvestPortfolio, asset.getTotal()))
						.build()
			);
		}
		log.info(String.format("[+] Finish data of diagram for user with investPortfolio '%d'", investPortfolio.getId()));
		return listDiagramResponseForm;
	}
}
