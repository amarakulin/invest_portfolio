const SET_DATA = 'SET_DATA';
const RESET_DATA = 'RESET_DATA';
const SET_DATA_INDEX = 'SET_DATA_INDEX';

export const widthPercent = 30;

const initialState = {
	tooltip: {
		top: null,
		left: null,
		title: '',
		data: []
	},
	showTooltip: false,
	mouseX: null,
	dataIndex: {
		left: 0,
		right: widthPercent
	}
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
		case SET_DATA_INDEX: {
			return {
				...state,
				dataIndex: {
					...action.index
				}
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

export const setDataIndex = (left, right) => ({type: SET_DATA_INDEX, index: {left, right}})

export default graphReduser;