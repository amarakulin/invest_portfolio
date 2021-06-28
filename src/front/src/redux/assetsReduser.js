import { CategoryApi, DataAPI } from "../api/api";
import { setSettedCategory, setCategories } from './categoryReduser';
import { setTableData } from './assetsTableReduser';
import { setTotalDiagrammData } from './diagrammReduser';
import { setTotalGraphData } from './graphReduser';
import graphDataConverter from '../utils/graphDataConverter'

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

const setTotalAssets = (totalAssets) => ({ type: SET_TOTAL_ASSETS, totalAssets });

const setTotalPrice = (totalPrice) => ({ type: SET_TOTAL_PRICE, totalPrice });

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

export const updateTotalData = () => (dispatch) => {
	DataAPI.getTotalPrice()
		.then(res => {
			dispatch(setTotalPrice(res));
		})
	DataAPI.getTotalAssets()
		.then(res => {
			dispatch(setTotalAssets(res));
		})
	DataAPI.getTableData()
		.then(data => {
			dispatch(setTableData(data))
		})
	DataAPI.getDiagrammData()
		.then(res => {
			dispatch(setTotalDiagrammData(res.data));
		})
	DataAPI.getGraphData()
		.then(res => {
			dispatch(setTotalGraphData(graphDataConverter(res.data)));
		})
	CategoryApi.getSettedCategory()
		.then(res => {
			dispatch(setSettedCategory(res.category));
		})
	CategoryApi.getCategories()
		.then(res => {
			dispatch(setCategories(res));
		})
}

export default assetsReduser;