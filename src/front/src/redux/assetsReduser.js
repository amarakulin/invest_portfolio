import { DataAPI } from "../api/api";

const SET_TOTAL_ASSETS = 'SET_TOTAL_ASSETS';

const initialState = {
	totalAssets: []
}

const assetsReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_TOTAL_ASSETS: {
			return {
				...state,
				totalAssets: [...action.totalAssets]
			}
		}
		default: {
			return state;
		}
	}
}

const setTotalAssets = (totalAssets) => ({type: SET_TOTAL_ASSETS, totalAssets});

export const getTotalAssets = () => (dispatch) => {
	DataAPI.getTotalAssets()
		.then(res => {
			dispatch(setTotalAssets(res));
		})
}

export default assetsReduser;