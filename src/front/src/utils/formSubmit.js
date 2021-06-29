import { FORM_ERROR } from 'final-form';
import { TYPE_BUY, TYPE_SELL } from '../redux/assetsTableReduser';
import createURLSearchParam from './createURLSearchParam';

export const addNewAssetsFormSubmit = (postNewAssetsData, newAssets, showAlert, updateTotalData) => async () => {
	const formData = [];

	for (let el of newAssets) {
		formData.push({
			ticker: el.ticker,
			amount: el.amount,
			type: el.type
		})
	}
	
	try {
		await postNewAssetsData(formData)
			.then(() => updateTotalData());
		showAlert('success', 'Актив успешно добавлен');
	} catch (e) {
		showAlert('danger', e.message);
	}
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

export const editAssetAmountFormSubmit = ({value, ticker, type, editAsset}, showAlert, updateTotalData) => async (formData) => {
	const initialValue = parseInt(value);

	try {
		if (type === TYPE_BUY) {
			await editAsset(ticker, +formData.amount + +initialValue)
				.then(() => updateTotalData());
		} else if (type === TYPE_SELL) {
			await editAsset(ticker, +initialValue - +formData.amount)
				.then(() => updateTotalData());
		}
		showAlert('success', 'Актив успешно изменен');
	} catch (e) {
		showAlert('danger', e.message);
	}
	
}

export const CreateCategoryFormSubmit = (createCategory, showAlert, updateTotalData) => async (formData) => {
	const data = {
		name: formData.name,
		value: []
	};

	for (let key in formData) {
		if (key !== 'name')
			data.value.push(key);
	}

	try {
		await createCategory(data)
			.then(() => updateTotalData());
		showAlert('success', 'Категория успешно создана');
	} catch (e) {
		showAlert('danger', e.message);
	}
}

export const SetCategoryFormSubmit = (setCategory, updateTotalData) => async (formData) => {
	await setCategory(formData)
		.then(() => updateTotalData());
}