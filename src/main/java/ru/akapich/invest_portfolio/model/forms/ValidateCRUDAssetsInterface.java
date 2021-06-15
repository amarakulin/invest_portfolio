package ru.akapich.invest_portfolio.model.forms;

import java.util.List;

/**
 * @author Aleksandr Marakulin
 **/

public interface ValidateCRUDAssetsInterface {

	boolean isTickersUnique(List<NewAssetsForm> listNewAssetsForm);
	NewAssetsForm notExistAsset(List<NewAssetsForm> listNewAssetsForm);
}
