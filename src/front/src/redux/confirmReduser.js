const OPEN_CONFIRM_WINDOW = 'OPEN_CONFIRM_WINDOW';
const CLOSE_CONFIRM_WINDOW = 'CLOSE_CONFIRM_WINDOW';
const CONFIRM = 'CONFIRM';
const CANCEL = 'CANCEL';

const initialState = {
	isOpen: false,
	hasConfirmed: false
}

const confirmReduser = (state = initialState, action) => {
	switch (action.type) {
		case OPEN_CONFIRM_WINDOW: {
			return {
				...state,
				isOpen: true
			}
		}
		case CLOSE_CONFIRM_WINDOW: {
			return {
				...state,
				isOpen: false
			}
		}
		case CONFIRM: {
			return {
				...state,
				hasConfirmed: true
			}
		}
		case CANCEL: {
			return {
				...state,
				hasConfirmed: false
			}
		}
		default: {
			return state;
		}
	}
}

const open = () => ({type: OPEN_CONFIRM_WINDOW});

const close = () => ({type: CLOSE_CONFIRM_WINDOW});

const confirm = () => ({type: CONFIRM});

const cancel = () => ({type: CANCEL});

export const confirmInterface = {
	open,
	close,
	confirm,
	cancel
}

export default confirmReduser;