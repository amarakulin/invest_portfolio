import * as axios from 'axios';

const api = axios.create({
	baseURL: 'http://localhost:8080/api/'
});

export const AuthAPI = {
	login(email, password, rememberMe = false) {
		return api.post('login', {
			email,
			password,
			rememberMe
		})
		.then(res => res.data)
	},
	logout() {
		return api.delete('login')
	},
	signUp(name, email, password, rePassword) {
		return api.post('signup', {
			name,
			email,
			password,
			rePassword
		})
		.then(res => res.data)
	}
}