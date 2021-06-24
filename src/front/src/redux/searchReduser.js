import { SearchAPI } from '../api/api'

const TOGGLE_SEARCH_IS_FETCHING = 'TOGGLE_SEARCH_IS_FETCHING';
const SET_MATCH_ASSETS = 'SET_MATCH_ASSETS';

const initialState = {
	isFetching: false,
	searchData: [],
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
				searchData: [...action.searchData]
			}
		}
		default: {
			return state;
		}
	}
}

const toggleIsFetching = (isFetching) => ({type: TOGGLE_SEARCH_IS_FETCHING, isFetching});

const setMatchAssets = (searchData) => ({type: SET_MATCH_ASSETS, searchData});

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