import { SearchAPI } from '../api/api'

const TOGGLE_SEARCH_IS_FETCHING = 'TOGGLE_SEARCH_IS_FETCHING';
const SET_MATCH_ASSETS = 'SET_MATCH_ASSETS';
const SET_SHOW_SEARCH = 'SET_SHOW_SEARCH';

const initialState = {
	showSearch: false,
	isFetching: false,
	data: [],
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
		case SET_SHOW_SEARCH: {
			return {
				...state,
				showSearch: action.showSearch
			}
		}
		default: {
			return state;
		}
	}
}

const toggleIsFetching = (isFetching) => ({type: TOGGLE_SEARCH_IS_FETCHING, isFetching});

const setMatchAssets = (data) => ({type: SET_MATCH_ASSETS, data});

export const setShowSearch = (showSearch) => ({type: SET_SHOW_SEARCH, showSearch});


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