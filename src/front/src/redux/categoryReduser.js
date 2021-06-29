import { CategoryApi } from '../api/api';

const SET_CATEGORIES = 'SET_CATEGORIES';
const SET_SETTED_CATEGORY = 'SET_SETTED_CATEGORY';

const initialState = {
	categories: [],
	settedCategory: null
}

const categoryResuser = (state = initialState, action) => {
	switch (action.type) {
		case SET_CATEGORIES: {
			return {
				...state,
				categories: [...action.categories]
			}
		}
		case SET_SETTED_CATEGORY: {
			return {
				...state,
				settedCategory: action.settedCategory
			}
		}
		default: {
			return state;
		}
	}
}

export const setCategories = (categories) => ({type: SET_CATEGORIES, categories});

export const setSettedCategory = (settedCategory) => ({type: SET_SETTED_CATEGORY, settedCategory});

export const getSettedCategory = () => (dispatch) => {
	CategoryApi.getSettedCategory()
		.then(res => {
			dispatch(setSettedCategory(res.category));
		})
}

export const getCategories = () => (dispatch) => {
	CategoryApi.getCategories()
		.then(res => {
			dispatch(setCategories(res))
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