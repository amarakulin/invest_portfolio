import authReduser, { setAuthUserData } from '../redux/authReduser'

it('Добавление значений в setAuthUserData', () => {
	// Исходные данные
	const initialState = {
		name: null,
		email: null,
		userID: null,
		isAuth: localStorage.getItem("isAuth") || false,
	}

	const action = setAuthUserData(1, 'akasha@mail.ru', 'akasha', true);

	// Вызываем reduser
	const newState = authReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({name: 'akasha', email: "akasha@mail.ru", userID: 1, isAuth: true});
})

it('Обнуление значений в setAuthUserData', () => {
	// Исходные данные
	const initialState = {
		name: 'akasha',
		email: "akasha@mail.ru",
		userID: 1,
		isAuth: true,
	}

	const action = setAuthUserData(null, null, null, false);

	// Вызываем reduser
	const newState = authReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({name: null, email: null ,userID: null ,isAuth: false});
})

it('Fake login should return error', () => {
	const fakeLogin = new Promise((resolve) => resolve({resultCode: 1, errorMessage: 'error'}))

	const processingLoginResponse = (res) => {
		if (res.resultCode === 0) {
			// dispatch(setAuthUserData(res.userID, res.email, res.login, res.isAuth));
		} else {
			return res.errorMessage;
		}
	}

	fakeLogin
		.then(processingLoginResponse)
		.then(res => {
			expect(res).toBe('error')
		})
})

it('Fake rember me should set value', () => {
	const fakeLogin = new Promise(resolve => {
		resolve({resultCode: 0, errorMessage: 'error',
			login: 'akasha', email: "akasha@mail.ru", userID: 1, isAuth: true})
	})

	const processingLoginResponse = (res) => {
		if (res.resultCode === 0) {
			// dispatch(setAuthUserData(res.userID, res.email, res.login, res.isAuth));
			if (res.isAuth)
				return 'setItem'
		} else {
			return res.errorMessage;
		}
	}

	fakeLogin
		.then(processingLoginResponse)
		.then(res => {
			expect(res).toBe('setItem')
		})
})

it("Fake rember me shouldn't set value", () => {
	const fakeLogin = new Promise(resolve => {
		resolve({resultCode: 0, errorMessage: 'error',
			login: 'akasha', email: "akasha@mail.ru", userID: 1, isAuth: false})
	})

	const processingLoginResponse = (res) => {
		if (res.resultCode === 0) {
			// dispatch(setAuthUserData(res.userID, res.email, res.login, res.isAuth));
			if (res.isAuth)
				return 'setItem'
			else if (!res.isAuth)
				return 'unsetItem'
		} else {
			return res.errorMessage;
		}
	}

	fakeLogin
		.then(processingLoginResponse)
		.then(res => {
			expect(res).toBe('unsetItem')
		})
})