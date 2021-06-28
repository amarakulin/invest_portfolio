import { AuthAPI } from '../api/api';
import createURLSearchParam from '../utils/createURLSearchParam'

export const SET_AUTH_USER_DATA = 'SET_AUTH_USER_DATA';


const initialState = {
	name: localStorage.getItem('name'),
	isAuth: localStorage.getItem('token') || false,
}

const authReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_AUTH_USER_DATA: {
			return {
				...state,
				...action.payload,
				isAuth: action.isAuth
			}
		}
		default: {
			return state;
		}
	}
}

export const setAuthUserData = (name, isAuth) => ({ type: SET_AUTH_USER_DATA, payload: {name}, isAuth});

const processingLogin = (params, dispatch) => {
	return AuthAPI.login(params)
		.then(res => {
			if (res === 'ok') {
				AuthAPI.getToken().then(res => {
					dispatch(setAuthUserData(res.name, true));
					localStorage.setItem('token', res.token);
					localStorage.setItem('name', res.name);
				})
			} else {
				throw new Error('Неверный e-mail или пароль');
			}
		})
		.catch(err => {
			throw new Error(err.message);
		})
}

export const login = (params) => (dispatch) => {
	return processingLogin(params, dispatch)
}

export const logout = () => (dispatch) => {
	AuthAPI.logout()
		.then(() => {
			localStorage.removeItem('token');
			localStorage.removeItem('name');
			dispatch(setAuthUserData(null, false));
		})
}

export const signUp = ({name, email, password, rePassword}) => (dispatch) => {
	return AuthAPI.signUp(name, email, password, rePassword)
		.then(async res => {
			if (res.resultCode === 0) {
				await processingLogin(createURLSearchParam({email, password}), dispatch);
			} else {
				throw new Error(res.error);
			}
		})
		.catch(err => {
			throw new Error(err.message);
		})
}

export default authReduser;