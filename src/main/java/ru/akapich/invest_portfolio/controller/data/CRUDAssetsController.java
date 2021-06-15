package ru.akapich.invest_portfolio.controller.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akapich.invest_portfolio.model.forms.AssetsResponseForm;
import ru.akapich.invest_portfolio.model.forms.NewAssetsForm;
import ru.akapich.invest_portfolio.model.forms.ValidateCRUDAssetsInterface;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for Crud assets
 *
 * @author Aleksandr Marakulin
 **/

@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PropertySource("classpath:message.properties")
public class CRUDAssets implements ValidateCRUDAssetsInterface {

	@Autowired
	private AllFinancialAssetRepository allFinancialAssetRepository;

	@Override
	public boolean isTickersUnique(List<NewAssetsForm> listNewAssetsForm) {

		int sizeUniqueAssets = listNewAssetsForm.stream()
				.map(NewAssetsForm::getTicker)
				.collect(Collectors.toSet())
				.size();
		return sizeUniqueAssets == listNewAssetsForm.size();
	}

	@Override
	public NewAssetsForm notExistAsset(List<NewAssetsForm> listNewAssetsForm) {
		Optional<NewAssetsForm> firstNotExistAssets = listNewAssetsForm.stream()
				.filter(a -> allFinancialAssetRepository.findByTicker(a.getTicker()) == null)
				.findFirst();
		log.info(String.format("[+] isAllTickersExist result is: %s", firstNotExistAssets.toString()));
		return firstNotExistAssets.orElse(null);

	}

	private AssetsResponseForm getAssetsResponseForm(List<NewAssetsForm> listAssetsForm){
		String errorMessage = "";

		NewAssetsForm firstNotExistAsset = notExistAsset(listAssetsForm);
		if (!isTickersUnique(listAssetsForm)){
			errorMessage = "{valid.repeat.assets}";
		}
		else if (firstNotExistAsset != null){
			errorMessage = "{valid.invalid.asset} : " + firstNotExistAsset.getTicker();
		}
		Integer resultCode = errorMessage.equals("") ? 0 : 1;

		return AssetsResponseForm.builder()
				.error(errorMessage)
				.resultCode(resultCode)
				.build();
	}

	private void addNewAssets(List<NewAssetsForm> listAssetsForm){

	}

	@PostMapping("/api/data/newassets")
	public AssetsResponseForm setNewAssets(@RequestBody List<NewAssetsForm> listAssetsForm, Model model){

		AssetsResponseForm assetsResponseForm = getAssetsResponseForm(listAssetsForm);
		if(assetsResponseForm.getResultCode() == 0){
			addNewAssets(listAssetsForm);
		}
		return assetsResponseForm;
	}
}
