import { FORM_ERROR } from 'final-form';
import { TYPE_BUY, TYPE_SELL } from '../redux/assetsTableReduser';

export const addNewAssetsFormSubmit = (postNewAssetsData, newAssets) => async (data) => {
	const formData = [];

	for (let el of newAssets) {
		formData.push({
			ticker: el.ticker,
			amount: el.amount,
			// type: el.type//TODO
		})
	}
	
	try {
		await postNewAssetsData(formData);
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
	let params = new URLSearchParams();

	for (let key in formData)
		params.append(key, formData[key]);

	try {
		await login(params);
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