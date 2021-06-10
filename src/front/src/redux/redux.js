import { createStore , combineReducers, applyMiddleware } from 'redux';
import authReduser from './authReduser.js';
import modalReduser from './modalReduser.js';
import graphReduser from './graphReduser.js';
import thunkMiddleware from 'redux-thunk';

const reduser = combineReducers({
	auth: authReduser,
	modal: modalReduser,
	graph: graphReduser
});

const store = createStore(reduser, applyMiddleware(thunkMiddleware));

export default store;

