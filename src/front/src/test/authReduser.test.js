import authReduser, { setAuthUserData } from '../redux/authReduser'

it('Добавление значений в setAuthUserData', () => {
	// Исходные данные
	const initialState = {
		name: null,
		isAuth: localStorage.getItem("isAuth") || false,
	}

	const action = setAuthUserData('akasha', true);

	// Вызываем reduser
	const newState = authReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({name: 'akasha', isAuth: true});
})

it('Обнуление значений в setAuthUserData', () => {
	// Исходные данные
	const initialState = {
		name: 'akasha',
		isAuth: true,
	}

	const action = setAuthUserData(null, false);

	// Вызываем reduser
	const newState = authReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({name: null, isAuth: false});
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
