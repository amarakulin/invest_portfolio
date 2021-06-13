import { DataAPI } from '../api/api';
import { toggleIsFetching } from './apiReduser';

const getChartData = () => ([
	{
		"name": "name 1",
		"ticker": "ticker 1",
		"percent": 20,
		"value": 500
	},
	{
		"name": "name 2",
		"ticker": "ticker 2",
		"percent": 30,
		"value": 5500
	},
	{
		"name": "name 3",
		"ticker": "ticker 3",
		"percent": 30,
		"value": 3900
	},
	{
		"name": "name 4",
		"ticker": "ticker 4",
		"percent": 10,
		"value": 3900
	},
	{
		"name": "name 5",
		"ticker": "ticker 5",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 6",
		"ticker": "ticker 6",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 7",
		"ticker": "ticker 7",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 8",
		"ticker": "ticker 8",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 9",
		"ticker": "ticker 9",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 10",
		"ticker": "ticker 10",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 11",
		"ticker": "ticker 11",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 12",
		"ticker": "ticker 12",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 13",
		"ticker": "ticker 13",
		"percent": 1,
		"value": 3900
	},
	{
		"name": "name 14",
		"ticker": "ticker 14",
		"percent": 0.5,
		"value": 3900
	},
	{
		"name": "name 15",
		"ticker": "ticker 14",
		"percent": 0.3,
		"value": 3900
	},
	{
		"name": "name 16",
		"ticker": "ticker 14",
		"percent": 0.2,
		"value": 3900
	},
])

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
			if (res.resultCode === 0) {
				dispatch(setTotalDiagrammData(res.data));
			}
			dispatch(toggleIsFetching(false));
		})
		.catch(err => {

		})
		.finally(() => {
			dispatch(setTotalDiagrammData(getChartData())); //!DELETE
			dispatch(toggleIsFetching(false));
		})
}

export default graphReduser;