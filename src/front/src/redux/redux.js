import { createStore , combineReducers, applyMiddleware } from 'redux';
import authReduser from './authReduser.js';
import graphReduser from './graphReduser.js';
import diagrammReduser from './diagrammReduser.js';
import searchReduser from './searchReduser.js';
import assetsTableReduser from './assetsTableReduser.js';
import apiReduser from './apiReduser.js';
import newAssetsReduser from './newAssetsReduser.js';
import thunkMiddleware from 'redux-thunk';

const reduser = combineReducers({
	auth: authReduser,
	graph: graphReduser,
	diagramm: diagrammReduser,
	search: searchReduser,
	newAssets: newAssetsReduser,
	table: assetsTableReduser,
	api: apiReduser
});

const store = createStore(reduser, applyMiddleware(thunkMiddleware));

export default store;

