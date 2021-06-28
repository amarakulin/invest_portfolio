import { DataAPI } from "../api/api";

const SET_TOTAL_ASSETS = 'SET_TOTAL_ASSETS';
const SET_TOTAL_PRICE = 'SET_TOTAL_PRICE';

const initialState = {
	totalAssets: [],
	totalPrice: 0
}

const assetsReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_TOTAL_ASSETS: {
			return {
				...state,
				totalAssets: [...action.totalAssets]
			}
		}
		case SET_TOTAL_PRICE: {
			return {
				...state,
				totalPrice: action.totalPrice
			}
		}
		default: {
			return state;
		}
	}
}

const setTotalAssets = (totalAssets) => ({type: SET_TOTAL_ASSETS, totalAssets});

const setTotalPrice = (totalPrice) => ({type: SET_TOTAL_PRICE, totalPrice});

export const getTotalAssets = () => (dispatch) => {
	DataAPI.getTotalAssets()
		.then(res => {
			dispatch(setTotalAssets(res));
		})
}

export const getTotalPrice = () => (dispatch) => {
	DataAPI.getTotalPrice()
		.then(res => {
			dispatch(setTotalPrice(res));
		})
}

export default assetsReduser;