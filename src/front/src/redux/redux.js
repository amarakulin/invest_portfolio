import { createStore , combineReducers, applyMiddleware } from 'redux';
import authReduser from './authReduser.js';
import thunkMiddleware from 'redux-thunk';

const reduser = combineReducers({
	auth: authReduser,
});

const store = createStore(reduser, applyMiddleware(thunkMiddleware));

export default store;

