export const CLOSE_MODAL = 'CLOSE_MODAL';
export const OPEN_MODAL = 'OPEN_MODAL';

const initialState = {
	isOpen: true
}

const modalReduser = (state = initialState, action) => {
	switch (action.type) {
		case CLOSE_MODAL: {
			return {
				...state,
				isOpen: false
			}
		}
		case OPEN_MODAL: {
			return {
				...state,
				isOpen: true
			}
		}
		default: {
			return state;
		}
	}
}

export const closeModal = () => ({type: CLOSE_MODAL});
export const openModal = () => ({type: OPEN_MODAL});

export default modalReduser;