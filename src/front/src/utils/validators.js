export const composeValidators = (...validators) => value =>
	validators.reduce((error, validator) => error || validator(value), undefined)

export const requiredField = (value) => {
	return value ? undefined : "Required";
}

export const emailValidator = (value) => {
	const reg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return reg.test(value) ? undefined : 'invalid email pattern';
}