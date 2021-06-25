const SHOW_ALERT = 'SHOW_ALERT';
const HIDE_ALERT = 'HIDE_ALERT';

const initialState = {
	type: null,
	visible: false,
	text: ''
};

const alertReduser = (state = initialState, action) => {
	switch (action.type) {
		case SHOW_ALERT: {
			return {
				...state,
				type: action.alertType,
				visible: true,
				text: action.alertText
			}
		}
		case HIDE_ALERT: {
			return {
				...state,
				type: null,
				visible: false,
				text: ''
			}
		}
		default: {
			return state;
		}
	}
}

export const showAlert = (alertType, alertText) => ({type: SHOW_ALERT, alertType: alertType, alertText: alertText});

export const hideAlert = () => ({type: HIDE_ALERT});

export default alertReduser;