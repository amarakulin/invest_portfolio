import { CategoryApi } from '../api/api';

const ADD_CATEGORY = 'ADD_CATEGORY';
const SET_CATEGORIES = 'SET_CATEGORIES';

const initialState = {
	categories: []
}

const categoryResuser = (state = initialState, action) => {
	switch (action.type) {
		case ADD_CATEGORY: {
			return {
				...state,
				categories: [...state.categories, action.category]
			}
		}
		case SET_CATEGORIES: {
			return {
				...state,
				categories: [...action.categories]
			}
		}
		default: {
			return state;
		}
	}
}

const addCategory = (category) => ({type: ADD_CATEGORY, category})

const setCategories = (categories) => ({type: ADD_CATEGORY, categories})

export const getCategories = () => (dispatch) => {//TODO добавить в init app
	return CategoryApi.getCategories()
		.then(res => {
			dispatch(setCategories(res.categories))
		})
}

export const createCategory = (data) => () => {
	return CategoryApi.createCategory(data)
		.then(res => {
			if (res.resultCode !== 0)
				throw new Error(res.error);
		})
}

export const setCategory = (category) => () => {
	return CategoryApi.setCategory(category);
}

export default categoryResuser;