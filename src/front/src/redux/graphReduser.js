const SET_TOOLTIP_DATA = 'SET_TOOLTIP_DATA';
const RESETE_TOOLTIP_DATA = 'RESETE_TOOLTIP_DATA';
const SET_MOUSE_X = 'SET_MOUSE_X';
const RESET_MOUSE_X = 'RESET_MOUSE_X';

const initialState = {
	tooltip: {
		showTooltip: false,
		top: null,
		left: null,
		title: '',
		data: []
	},
	mouseX: null
}

const graphReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_TOOLTIP_DATA: {
			return {
				...state,
				tooltip: {
					...action.tooltip
				}
			}
		}
		case RESETE_TOOLTIP_DATA: {
			return {
				...state,
				tooltip: {
					...action.tooltip
				}
			}
		}
		case SET_MOUSE_X: {
			return {
				...state,
				mouseX: action.x
			}
		}
		case RESET_MOUSE_X: {
			return {
				...state,
				mouseX: null
			}
		}
		default: {
			return state;
		}
	}
}

export const setTooltipData = ({top, left, title, data}) => {
	return {
		type: SET_TOOLTIP_DATA,
		tooltip: {
			showTooltip: true,
			top,
			left,
			title,
			data
		}
	}
}

export const resetTooltipData = () => {
	return {
		type: SET_TOOLTIP_DATA,
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		}
	}
}

export const setMouseX = (x) => ({type: SET_MOUSE_X, x})
export const resetMouseX = () => ({type: SET_MOUSE_X})

export default graphReduser;