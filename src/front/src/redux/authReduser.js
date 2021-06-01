const TOGGE_REMEMBER_USER = 'TOGGE_REMEMBER_USER';

const initialState = {
	isAuthenticated: localStorage.getItem("isAuth"),
	rememberAuthentication: false
}

const authReduser = (state = initialState, action) => {
	switch (action.type) {
		case TOGGE_REMEMBER_USER: {
			return {
				...state,
				rememberAuthentication: !state.rememberAuthentication
			}
		}
		default: {
			return state;
		}
	}
}

export const toggleAuthenticationAC = () => ({type: TOGGE_REMEMBER_USER})

export default authReduser;