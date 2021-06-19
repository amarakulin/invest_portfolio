import { DataAPI } from '../api/api';
import { toggleIsFetching } from './apiReduser';

const TOGGLE_IS_FETCHING = 'TOGGLE_IS_FETCHING';
const SET_TABLE_DATA = 'SET_TABLE_DATA';

const initialState = {
	isFetching: false,
	data: {}
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
				data: {...action.data}
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
		header: {
			name: 'Название',
			ticker: 'Тикер',
			type: 'Тип актива',
			exchange: 'Биржа',
			price: 'Цена.',
			amount: 'Кол-во',
			total: 'Сумма'
		},
		body: [
			{
				name: 'Газпром',
				ticker: 'GAZP',
				type: 'акция',
				exchange: 'MOEX',
				price: '180руб.',
				amount: '3 шт.',
				total: '540 руб.'
			},
			{
				name: 'Газпром',
				ticker: 'GAZP',
				type: 'акция',
				exchange: 'MOEX',
				price: '180руб.',
				amount: '3 шт.',
				total: '540 руб.'
			},
			{
				name: 'Газпром',
				ticker: 'GAZP',
				type: 'акция',
				exchange: 'MOEX',
				price: '180руб.',
				amount: '3 шт.',
				total: '540 руб.'
			}
			
		],
		options: {
			delete: true,
			change: 'amount'
		},
		order: [
			'name', 'ticker', 'type', 'exchange', 'price', 'amount', 'total', 'delete'
		],
	}
}
