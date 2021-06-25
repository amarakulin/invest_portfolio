const SHOW_ALERT = 'SHOW_ALERT';
const HIDE_ALERT = 'HIDE_ALERT';

const initialState = {
	type: 'success',
	visible: true
};

const alertReduser = (state = initialState, action) => {
	switch (action.type) {
		case SHOW_ALERT: {
			return {
				type: action.alertType,
				visible: true
			}
		}
		case HIDE_ALERT: {
			return {
				type: null,
				visible: false
			}
		}
		default: {
			return state;
		}
	}
}

export const showAlert = (alertType) => ({type: SHOW_ALERT, alertType: alertType});

export const hideAlert = () => ({type: HIDE_ALERT});

export default alertReduser;