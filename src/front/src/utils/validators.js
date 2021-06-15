import { newAssetsDataConverter } from './newAssetsDataConverter'

export const composeValidators = (...validators) => value =>
	validators.reduce((error, validator) => error || validator(value), undefined)

export const requiredField = (value) => {
	return value ? undefined : "Поле является обязтельным";
}

export const emailValidator = (value) => {
	const reg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return reg.test(value) ? undefined : 'Неверный формат e-mail';
}

export const validateRepasswordField = (values) => {
	const errors = {}

	if (values.password !== values.rePassword)
		errors.password = 'Пароли должны совпадать'
	return errors
}

export const validateIdenticalName = (value) => {
	const errors = {};

	let tickerArr = [];

	newAssetsDataConverter(value).value.forEach(el => {
		tickerArr.push(el.ticker);
	})

	const tickerSet = Array.from([...new Set(tickerArr)]);

	if (tickerArr.length != tickerSet.length)
		errors.identical = 'Поля не должны иметь одинаковые имена';

	return errors;
}