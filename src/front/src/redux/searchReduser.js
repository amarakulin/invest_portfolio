import { SearchAPI } from '../api/api'

const TOGGLE_SEARCH_IS_FETCHING = 'TOGGLE_SEARCH_IS_FETCHING';
const SET_MATCH_ASSETS = 'SET_MATCH_ASSETS';
const INCREASE_NEW_ASSETS_NUMBER = 'INCREASE_NEW_ASSETS_NUMBER';

const initialState = {
	isFetching: false,
	data: [],
	newAssetsNumber: 1
}

const searchReduser = (state = initialState, action) => {
	switch (action.type) {
		case TOGGLE_SEARCH_IS_FETCHING: {
			return {
				...state,
				isFetching: action.isFetching
			}
		}
		case SET_MATCH_ASSETS: {
			return {
				...state,
				data: [...action.data]
			}
		}
		case INCREASE_NEW_ASSETS_NUMBER: {
			return {
				...state,
				newAssetsNumber: state.newAssetsNumber + 1
			}
		}
		default: {
			return state;
		}
	}
}

const toggleIsFetching = (isFetching) => ({type: TOGGLE_SEARCH_IS_FETCHING, isFetching});

const setMatchAssets = (data) => ({type: SET_MATCH_ASSETS, data});

export const increaseNewAssetsNumber = () => ({type: INCREASE_NEW_ASSETS_NUMBER})


export const getMatchAssets = (text) => (dispatch) => {
	dispatch(toggleIsFetching(true));
	SearchAPI.getMatchAssets(text)
		.then(res => {
			if (res.resultCode === 0) {
				dispatch(setMatchAssets(res));
			}
			dispatch(toggleIsFetching(false));
		})
		.finally(() => {
			dispatch(toggleIsFetching(false));
			dispatch(setMatchAssets(res)); //! DELETE
		})
}

export default searchReduser;

const res = [
	{
		ticker: 'AXC',
		name: 'APPLE',
		type: 'акция'
	},
	{
		ticker: 'ZKJ',
		name: 'MICROSOFT',
		type: 'акция'
	},
	{
		ticker: 'ANC',
		name: 'ahsjdbajskdnasjahsjdbajskdnasjahsjdbajskdnasjahsjdbajskdnasjahsjdbajskdnasj',
		type: 'акция'
	}
]