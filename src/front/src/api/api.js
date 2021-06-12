import * as axios from 'axios';

const api = axios.create({
	baseURL: 'http://localhost:8081/api/'
});

export const AuthAPI = {
	login(email, password, rememberMe = false) {
		return api.post('auth/login', {
			email,
			password,
			rememberMe
		})
		.then(res => res.data)
	},
	logout() {
		return api.delete('auth/login')
	},
	signUp(name, email, password, rePassword) {
		return api.post('auth/signup', {
			name,
			email,
			password,
			rePassword
		})
		.then(res => res.data)
	}
}

export const DataAPI = {
	getGraphData() {
		return api.get('data/graph')
	}
}