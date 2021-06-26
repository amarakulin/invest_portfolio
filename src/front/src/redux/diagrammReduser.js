import { DataAPI } from '../api/api';
import { toggleIsFetching } from './apiReduser';

const SET_TOTAL_DIAGRAMM_DATA = 'SET_TOTAL_DIAGRAMM_DATA';
const SET_ACTIVE_CIRCLE_INDEX = 'SET_ACTIVE_CIRCLE_INDEX';

const initialState = {
	data: null,
	activeIndex: null
}

const graphReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_TOTAL_DIAGRAMM_DATA: {
			return {
				...state,
				data: [...action.data]
			}
		}
		case SET_ACTIVE_CIRCLE_INDEX: {
			return {
				...state,
				activeIndex: action.activeIndex
			}
		}
		default: {
			return state;
		}
	}
}

export const setTotalDiagrammData = (data) => ({type: SET_TOTAL_DIAGRAMM_DATA, data})

export const setActiveCircle = (activeIndex) => ({type: SET_ACTIVE_CIRCLE_INDEX, activeIndex})

export const getDiagrammData = () => (dispatch) => {
	dispatch(toggleIsFetching(true));
	DataAPI.getDiagrammData()
		.then(res => {
			dispatch(setTotalDiagrammData(res.data));
			dispatch(toggleIsFetching(false));
		})
}

export default graphReduser;