import * as axios from 'axios';

const api = axios.create({
	baseURL: 'http://localhost:8080/api/'
});

export const AuthAPI = {
	login(login, password, rememberMe = false) {
		return api.post('login', {
			login,
			password,
			rememberMe
		})
	},
	logout() {
		return api.delete('login')
	}
}