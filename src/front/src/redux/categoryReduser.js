import { CategoryApi } from '../api/api';

const initialState = {

}

const categoryResuser = (state = initialState, action) => {
	switch (action.type) {
		default: {
			return state;
		}
	}
}

export const createCategory = (data) => () => {
	return CategoryApi.createCategory(data)
		.then(res => {
			if (res.resultCode !== 0)
				throw new Error(res.error);
		})
}

export default categoryResuser;