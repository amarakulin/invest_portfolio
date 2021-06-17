import { FORM_ERROR } from 'final-form';

export const addNewAssetsFormSubmit = (postNewAssetsData, newAssets) => async (data) => {
	const formData = [];
	
	for (let el of newAssets) {
		formData.push({
			ticker: el.ticker,
			amount: el.amount,
			type: el.type
		})
	}
	console.log(formData)
	const error = await postNewAssetsData(formData);

	if (error) {
		return { [FORM_ERROR]: error }
	}
	//TODO после отправки нужно заново запросить данные пользователя
}