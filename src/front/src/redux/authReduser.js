const SET_REMEMBER_USER = 'SET_REMEMBER_USER';

const initialState = {
	isAuthenticated: localStorage.getItem("isAuth"),
	rememberMe: false
}

const authReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_REMEMBER_USER: {
			return {
				...state,
				rememberMe: action.isRememberMe
			}
		}
		default: {
			return state;
		}
	}
}

export const toggleAuthenticationAC = (isRememberMe) => ({type: SET_REMEMBER_USER, isRememberMe})

export default authReduser;