import { newAssetsDataConverter } from './newAssetsDataConverter';
import { FORM_ERROR } from 'final-form';

export const addNewAssetsFormSubmit = (postNewAssetsData) => async (data) => {
	const formData = newAssetsDataConverter(data);

	const error = await postNewAssetsData(formData);

	if (error) {
		return { [FORM_ERROR]: error }
	}
	//TODO после отправки нужно заново запросить данные пользователя
}