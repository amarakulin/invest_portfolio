export const composeValidators = (...validators) => value =>
	validators.reduce((error, validator) => error || validator(value), undefined)

export const requiredField = (value) => {
	return value ? undefined : "Поле является обязательным";
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

export const amountValidator = (value) => {
	if (+value <= 0 || isNaN(+value))
		return true
	return undefined;
}

export const validateIdenticalName = (data) => {
	return (value) => {

		for (let el of data) {
			if (el.ticker === value)
				return 'Такой актив уже добавлен';
		}
		return undefined;
	}
}

export const validateSearchUnknownTicker = (data) => (value) => {
	if (!value)
		return undefined;
	if (data.every(el => el.ticker !== value))
		return 'Данный тикер не найден';
	return undefined;
}