import { FORM_ERROR } from 'final-form';
import { TYPE_BUY, TYPE_SELL } from '../redux/assetsTableReduser';
import createURLSearchParam from './createURLSearchParam'

export const addNewAssetsFormSubmit = (postNewAssetsData, newAssets, showAlert) => async () => {
	const formData = [];

	for (let el of newAssets) {
		formData.push({
			ticker: el.ticker,
			amount: el.amount,
		})
	}
	
	try {
		await postNewAssetsData(formData);
		showAlert('success', 'Актив успешно добавлен');
	} catch (e) {
		return { [FORM_ERROR]: e.message }
	}

	//TODO после отправки нужно заново запросить данные пользователя
}

export const signUpFormSubmit = (signUp) => async (formData) => {
	try {
		await signUp(formData);
	} catch (e) {
		return { [FORM_ERROR]: e.message }
	}
}

export const authFormSubmit = (login) => async (formData) => {
	try {
		await login(createURLSearchParam(formData));
	} catch (e) {
		return { [FORM_ERROR]: e.message }
	}
}

export const editAssetAmountFormSubmit = ({value, ticker, type, editAsset}) => async (formData) => {
	const initialValue = parseInt(value);

	if (type === TYPE_BUY) {
		editAsset(ticker, +formData.amount + +initialValue);
	} else if (type === TYPE_SELL) {
		editAsset(ticker, +initialValue - +formData.amount);
	}
}