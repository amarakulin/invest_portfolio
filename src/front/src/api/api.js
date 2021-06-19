import * as axios from 'axios';

const API_URL = 'http://localhost:8081/api/'

const api = axios.create({
	baseURL: API_URL,
	withCredentials: true,
});

api.interceptors.request.use(config => {
	config.headers.Authorization = `Bearer ${localStorage.getItem('token')}`
	return config;
})

export const AuthAPI = {
	login(params) {
		return api.post('auth/login', params, {
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			  }
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
	},
	getDiagrammData() {
		return api.get('data/diagramm')
	},
	postNewAssetsData(data) {
		return api.post('data/newassets', data)
			.then(res => res.data)
	},
	getTableData() {
		return api.get('data/tableassets')
			.then(res => res.data)
	}
}

export const SearchAPI = {
	getMatchAssets(params) {
		return api.get(`data/matchassets?ticker=${params}`)
			.then(res => res.data);
	}
}