import * as axios from 'axios';

const instanse = axios.create({
	baseURL: 'http://localhost:8080/api/'
});

export const AuthAPI = {
	login(login, password, rememberMe = false) {
		return instanse.post('login', {
			login,
			password,
			rememberMe
		})
	}
}