import { DataAPI } from '../api/api'

const REMOVE_NEW_ASSET = 'REMOVE_NEW_ASSET';
const ADD_NEW_ASSET = 'ADD_NEW_ASSET';
const RESET_NEW_ASSETS = 'RESET_NEW_ASSETS'; 

const initialState = {
	newAssets: [],
	nessesaryField: ['ticker', 'name', 'type']
}

const newAssetsReduser = (state = initialState, action) => {
	switch (action.type) {
		case REMOVE_NEW_ASSET: {
			return {
				...state,
				newAssets: state.newAssets.filter((el) => el.ticker != action.id)
			}
		}
		case ADD_NEW_ASSET: {
			return {
				...state,
				newAssets: [...state.newAssets, action.asset]
			}
		}
		case RESET_NEW_ASSETS: {
			return {
				...state,
				newAssets: []
			}
		}
		default: {
			return state;
		}
	}
}

export const addNewAsset = (asset) => ({type: ADD_NEW_ASSET, asset})

export const removeNewAsset = (id) => ({type: REMOVE_NEW_ASSET, id})

const resetNewAssets = () => ({type: RESET_NEW_ASSETS})

export const postNewAssetsData = (data) => (dispatch) => {
	return DataAPI.postNewAssetsData(data)
		.then(res => {
			if (res.resultCode !== 0) {
				return res.error
			} else {
				dispatch(resetNewAssets());
			}
		})
		.catch(err => {
			return err.message
		})
}

export default newAssetsReduser;