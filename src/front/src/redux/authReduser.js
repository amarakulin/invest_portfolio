import { AuthAPI } from '../api/api';

export const SET_AUTH_USER_DATA = 'SET_AUTH_USER_DATA';


const initialState = {
	login: null,
	email: null,
	userID: null,
	isAuth: localStorage.getItem('isAuth'),
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

export const setAuthUserData = (userID, email, login, isAuth) => ({ type: SET_AUTH_USER_DATA, payload: {userID, email, login}, isAuth});

export const login = (login, password, rememberMe) => (dispatch) => {
	return AuthAPI.login(login, password, rememberMe)
		.then(res => {
			if (res.resultCode === 0) {
				dispatch(setAuthUserData(res.userID, res.email, res.login, res.isAuth)); //TODO getAuthUserData для получения информации залогиненого пользователя

				if (rememberMe)
					localStorage.setItem('isAuth', true);
				else
					localStorage.removeItem('isAuth');
				
			} else {
				return res.errorMessage;
			}
		})
		.catch(err => {
			return err.message
		})
}

export default authReduser;