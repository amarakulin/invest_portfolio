const OPEN_CONFIRM = 'OPEN_CONFIRM';
const CLOSE_CONFIRM = 'CLOSE_CONFIRM'

const initialState = {
	isOpen: false,
	instanceID: '',
	confirmCallback: () => {
		throw new Error('confirm callback function must be setted')
	}	
}

const confirmReduser = (state = initialState, action) => {
	switch (action.type) {
		case OPEN_CONFIRM: {
			return {
				...state,
				isOpen: true,
				instanceID: action.id,
				confirmCallback: action.confirmCallback
			}
		}
		case CLOSE_CONFIRM: {
			return {
				...state,
				isOpen: false,
				instanceID: initialState.instanceID,
				confirmCallback: initialState.confirmCallback,
			}
		}
		default: {
			return state;
		}
	}
}

export const openConfirm = (id, confirmCallback) => ({type: OPEN_CONFIRM, id, confirmCallback});

export const closeConfirm = () => ({type: CLOSE_CONFIRM})

export default confirmReduser;