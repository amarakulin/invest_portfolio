const UPDATE_EMAIL_INPUT = 'UPDATE_EMAIL_INPUT';
const UPDATE_PASSWORD_INPUT = 'UPDATE_PASSWORD_INPUT';

const initialState = {
	eamailInput: "",
	passwordInput: ""
}

const authPageReduser = (state = initialState, action) => {
	switch (action.type) {
		case UPDATE_EMAIL_INPUT: {
			return {
				...state,
				eamailInput:action.newEmailText
			}
		}
		case UPDATE_PASSWORD_INPUT: {
			return {
				...state,
				passwordInput: action.newPasswordText
			}
		}
		default: {
			return state;
		}
	}
}

export const updateEmailInput = (text) => ({type: UPDATE_EMAIL_INPUT, newEmailText: text})
export const updatePasswordInput = (text) => ({type: UPDATE_PASSWORD_INPUT, newPasswordText: text})


export default authPageReduser;