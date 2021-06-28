import { createStore , combineReducers, applyMiddleware } from 'redux';
import authReduser from './authReduser.js';
import confirmReduser from './confirmReduser.js';
import categoryReduser from './categoryReduser.js';
import alertReduser from './alertReduser.js';
import graphReduser from './graphReduser.js';
import diagrammReduser from './diagrammReduser.js';
import searchReduser from './searchReduser.js';
import assetsTableReduser from './assetsTableReduser.js';
import apiReduser from './apiReduser.js';
import newAssetsReduser from './newAssetsReduser.js';
import thunkMiddleware from 'redux-thunk';
import assetsReduser from './assetsReduser.js';

const reduser = combineReducers({
	auth: authReduser,
	graph: graphReduser,
	diagramm: diagrammReduser,
	search: searchReduser,
	newAssets: newAssetsReduser,
	table: assetsTableReduser,
	alert: alertReduser,
	category: categoryReduser,
	api: apiReduser,
	assets: assetsReduser,
	confirm: confirmReduser
});

const store = createStore(reduser, applyMiddleware(thunkMiddleware));

export default store;

