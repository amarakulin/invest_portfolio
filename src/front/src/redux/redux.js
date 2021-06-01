import { createStore , combineReducers } from 'redux';
import authReduser from './authReduser.js';
import authPageReduser from './authPageReduser.js';

const reduser = combineReducers({
	auth: authReduser,
	authPage: authPageReduser
});

const store = createStore(reduser);

export default store;

