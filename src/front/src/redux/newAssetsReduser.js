const REMOVE_NEW_ASSET = 'REMOVE_NEW_ASSET';
const ADD_NEW_ASSET = 'ADD_NEW_ASSET';

const initialState = {
	newAssets: []
}

const newAssetsReduser = (state = initialState, action) => {
	switch (action.type) {
		case REMOVE_NEW_ASSET: {
			
		}
		case ADD_NEW_ASSET: {
			return {
				...state,
				newAssets: [...state.newAssets, (action.asset)]
			}
		}
		default: {
			return state;
		}
	}
}

export const addNewAsset = (asset) => ({type: ADD_NEW_ASSET, asset})

export default newAssetsReduser;