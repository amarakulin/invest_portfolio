import { DataAPI, AssetsOptionsApi } from '../api/api';
import { toggleIsFetching } from './apiReduser';

export const TYPE_BUY = 'TYPE_BUY';
export const TYPE_SELL = 'TYPE_SELL';

const TOGGLE_IS_FETCHING = 'TOGGLE_IS_FETCHING';
const SET_TABLE_DATA = 'SET_TABLE_DATA';
const DELETE_ASSET = 'DELETE_ASSET';
const EDIT_ASSET = 'EDIT_ASSET';
const SET_SELECTED_ASSET = 'SET_SELECTED_ASSET';
const RESET_SELECTED_ASSET = 'RESET_SELECTED_ASSET';

const initialState = {
	isFetching: false,
	data: {},
	selectedAsset: {
		ticker: null,
		type: null
	}
}

const assetsTableReduser = (state = initialState, action) => {
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
		case DELETE_ASSET: {
			return {
				...state,
				data: {
					...state.data,
					body: state.data.body.filter(el => {
						return el.ticker !== action.ticker
					})
				}
			}
		}
		case EDIT_ASSET: {
			return {
				...state,
				data: {
					...state.data,
					body: state.data.body.map(el => {
						if (el.ticker === action.ticker) {
							el.amount = `${action.amount} шт.`
						}
						return el;
					})
				}
			}
		}
		case SET_SELECTED_ASSET: {
			return {
				...state,
				selectedAsset: {...action.selectedAsset}
			}
		}
		case RESET_SELECTED_ASSET: {
			return {
				...state,
				selectedAsset: {...initialState.selectedAsset}
			}
		}
		default: {
			return state;
		}
	}
}

const setTableData = (data) => ({type: SET_TABLE_DATA, data})

const deleteAssetFromState = (ticker) => ({type: DELETE_ASSET, ticker})

const editAssetInState = (ticker, amount) => ({type: EDIT_ASSET, ticker, amount})

export const setSelectedAsset = (selectedAsset) => ({type: SET_SELECTED_ASSET, selectedAsset})

export const resetSelectedAsset = () => ({type: RESET_SELECTED_ASSET})

export const editAsset = (ticker, amount) => (dispatch) => {
	AssetsOptionsApi.editAsset(ticker, amount)
		.then(() => {
			dispatch(editAssetInState(ticker, amount));
		})
}

export const deleteAsset = (ticker) => (dispatch) => {
	AssetsOptionsApi.deleteAsset(ticker)
		.then(() => dispatch(deleteAssetFromState(ticker)))
}

export const getTableData = () => (dispatch) => {
	dispatch(toggleIsFetching(true));
	DataAPI.getTableData()
		.then(data => {
			dispatch(setTableData(data))
			dispatch(toggleIsFetching(false));
		})
}

export default assetsTableReduser;

function getTableDataTest() { //! DELETE
	return {
		header: {
			name: 'Название',
			ticker: 'Тикер',
			type: 'Тип актива',
			exchange: 'Биржа',
			price: 'Цена.',
			amount: 'Кол-во',
			total: 'Сумма',
		},
		body: [
			{
				name: 'Газпром',
				ticker: 'GAZP',
				type: 'акция',
				exchange: 'MOEX',
				price: '180руб.',
				amount: '3 шт.',
				total: '540 руб.', 
			},
			{
				name: 'Газпром',
				ticker: 'MAG',
				type: 'акция',
				exchange: 'MOEX',
				price: '180руб.',
				amount: '3 шт.',
				total: '540 руб.',
			},
			{
				name: 'Газпром',
				ticker: 'ZOP',
				type: 'акция',
				exchange: 'MOEX',
				price: '180руб.',
				amount: '3 шт.',
				total: '540 руб.',
			}
			
		],
		order: [
			'name', 'ticker', 'type', 'exchange', 'price', 'amount', 'total'
		],
	}
}
