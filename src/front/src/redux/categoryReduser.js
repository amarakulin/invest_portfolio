import { CategoryApi } from '../api/api';

const ADD_CATEGORY = 'ADD_CATEGORY';
const SET_CATEGORIES = 'SET_CATEGORIES';
const SET_SETTED_CATEGORY = 'SET_SETTED_CATEGORY';

const initialState = {
	categories: [],
	settedCategory: null
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
		case SET_SETTED_CATEGORY: {
			return {
				...state,
				setedCategory: action.setedCategory
			}
		}
		default: {
			return state;
		}
	}
}

const addCategory = (category) => ({type: ADD_CATEGORY, category});

const setCategories = (categories) => ({type: ADD_CATEGORY, categories});

const setSettedCategory = (setedCategory) => ({type: SET_SETTED_CATEGORY, setedCategory});

export const getSettedCategory = () => (dispatch) => {
	CategoryApi.getSettedCategory()
		.then(res => {
			dispatch(setSettedCategory(res.category));
		})
}

export const getCategories = () => (dispatch) => {//TODO добавить в init app
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
	CategoryApi.setCategory(category);
}

export default categoryResuser;