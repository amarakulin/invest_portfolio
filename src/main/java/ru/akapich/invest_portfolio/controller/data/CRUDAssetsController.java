package ru.akapich.invest_portfolio.controller.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.akapich.invest_portfolio.model.forms.assets.AssetsResponseForm;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsForm;
import ru.akapich.invest_portfolio.model.forms.ValidateCRUDAssetsInterface;
import ru.akapich.invest_portfolio.model.forms.assets.NewAssetsFormList;
import ru.akapich.invest_portfolio.repository.portfolio.asset_data.store_assets.AllFinancialAssetRepository;
import ru.akapich.invest_portfolio.service.portfolio.asset_data.store_assets.Impl.AddingNewListFinancialAssetsImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for Crud assets
 *
 * @author Aleksandr Marakulin
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
class Funny{
	private List<NewAssetsForm> newAssetsFormList;
}






@Log4j2
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PropertySource("classpath:message.properties")
public class CRUDAssetsController implements ValidateCRUDAssetsInterface {

	@Autowired
	private AddingNewListFinancialAssetsImpl addingNewListFinancialAsset;

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



//	@PostMapping(value = "/api/data/newassets", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/api/data/newassets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AssetsResponseForm setNewAssets(@RequestBody List<NewAssetsForm> listAssetsForm, Model model){
		log.info("Start: setNewAssets");
//		AssetsResponseForm assetsResponseForm = getAssetsResponseForm(listAssetsForm);
//		log.info(String.format("Get a result of response error: %s", assetsResponseForm.getError()));
////		//FIXME May be check if user in the session
//		if(assetsResponseForm.getResultCode() == 0){
//			addingNewListFinancialAsset.addNewAssets(listAssetsForm);
//		}
		log.info("End: setNewAssets");
//		return assetsResponseForm;
		return new AssetsResponseForm();
	}
}
