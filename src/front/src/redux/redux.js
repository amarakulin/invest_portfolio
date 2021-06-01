import { createStore , combineReducers } from 'redux';
import authReduser from './authReduser.js';

const reduser = combineReducers({
	auth: authReduser
});

const store = createStore(reduser);

export default store;

