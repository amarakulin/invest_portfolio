import { DataAPI } from '../api/api';
import { toggleIsFetching } from './apiReduser';

const TOGGLE_IS_FETCHING = 'TOGGLE_IS_FETCHING';
const SET_TABLE_DATA = 'SET_TABLE_DATA';

const initialState = {
	isFetching: false,
	data: []
}

const tableReduser = (state = initialState, action) => {
	switch (action.type) {
		case TOGGLE_IS_FETCHING: {
			return {
				...state,
				isFetching: action.isFetching
			}
		}
		case SET_TABLE_DATA: {
			return {
				...state,
				data: [...action.data]
			}
		}
		default: {
			return state;
		}
	}
}

const setTableData = (data) => ({type: SET_TABLE_DATA, data})

export const getTableData = () => (dispatch) => {
	dispatch(toggleIsFetching(true));
	DataAPI.getTableData()
		.then(res => {
			if (res.resulCode === 0) {
				dispatch(setTableData(res.data))
			}
			dispatch(toggleIsFetching(false));
		})
		.catch(err => {
			
		})
		.finally(() => {
			dispatch(setTableData(getTableDataTest())) //! DELETE
			dispatch(toggleIsFetching(false));
		})
}

export default tableReduser;

function getTableDataTest() {
	return {
		header: [
			'Название', 'Тикер', 'Тип актива', 'Биржа', 'Цена', 'Кол-во', 'Сумма'
		],
		body: {
			1: ['Газпром', 'GAZP', 'акция', 'MOEX', '180руб.', '3 шт.', '540 руб.'],
			2: ['Газпром', 'GAZP', 'акция', 'MOEX', '180руб.', '3 шт.', '540 руб.'],
			3: ['Газпром', 'GAZP', 'акция', 'MOEX', '180руб.', '3 шт.', '540 руб.'],
			4: ['Газпром', 'GAZP', 'акция', 'MOEX', '180руб.', '3 шт.', '540 руб.'],
		}
	}
}