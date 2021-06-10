const SET_DATA = 'SET_DATA'
const RESET_DATA = 'RESET_DATA'

const initialState = {
	tooltip: {
		top: null,
		left: null,
		title: '',
		data: []
	},
	showTooltip: false,
	mouseX: null
}

const graphReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_DATA: {
			return {
				...state,
				tooltip: {
					...action.tooltip
				},
				showTooltip: true,
				mouseX: action.x
			}
		}
		case RESET_DATA: {
			return {
				...state,
				tooltip: {
					top: null,
					left: null,
					title: '',
					data: []
				},
				showTooltip: false,
				mouseX: null
			}
		}
		default: {
			return state;
		}
	}
}

export const setData = ({top, left, title, data, x}) => ({
	type: SET_DATA,
	tooltip: {
		top,
		left,
		title,
		data
	},
	x: x
})

export const resetData = () => ({
	type: RESET_DATA,
})

export default graphReduser;