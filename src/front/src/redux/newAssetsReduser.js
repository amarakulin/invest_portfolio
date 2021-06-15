import { DataAPI } from '../api/api'

const REMOVE_NEW_ASSET = 'REMOVE_NEW_ASSET';
const ADD_NEW_ASSET = 'ADD_NEW_ASSET';

const initialState = {
	newAssets: []
}

const newAssetsReduser = (state = initialState, action) => {
	switch (action.type) {
		case REMOVE_NEW_ASSET: {
			if (state.newAssets.filter((el) => el.props.id != action.id).length === 0) {
				return {
					...state
				}
			}
			return {
				...state,
				newAssets: state.newAssets.filter((el) => el.props.id != action.id)
			}
		}
		case ADD_NEW_ASSET: {
			return {
				...state,
				newAssets: [...state.newAssets, action.asset]
			}
		}
		default: {
			return state;
		}
	}
}

export const addNewAsset = (asset) => ({type: ADD_NEW_ASSET, asset})

export const removeNewAsset = (id) => ({type: REMOVE_NEW_ASSET, id})

export const postNewAssetsData = (data) => (dispatch) => {
	return DataAPI.postNewAssetsData(data)
		.then(res => {
			if (res.resultCode !== 0) {
				return res.error
			}
		})
		.catch(err => {
			return err.message
		})
}

export default newAssetsReduser;