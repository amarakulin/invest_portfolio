import { createStore , combineReducers, applyMiddleware } from 'redux';
import authReduser from './authReduser.js';
import modalReduser from './modalReduser.js';
import graphReduser from './graphReduser.js';
import diagrammReduser from './diagrammReduser.js';
import apiReduser from './apiReduser.js';
import thunkMiddleware from 'redux-thunk';

const reduser = combineReducers({
	auth: authReduser,
	modal: modalReduser,
	graph: graphReduser,
	diagramm: diagrammReduser,
	api: apiReduser
});

const store = createStore(reduser, applyMiddleware(thunkMiddleware));

export default store;

