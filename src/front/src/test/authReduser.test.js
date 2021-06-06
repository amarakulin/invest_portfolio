import authReduser, { setUserData } from '../redux/authReduser'

it('123', () => {
	// Исходные данные
	const initialState = {
		login: null,
		email: null,
		userID: null,
		isAuth: localStorage.getItem("isAuth") || false,
	}

	const action = setUserData(1, 'akasha@,ail.ru', 'akasha', true);

	// Вызываем reduser
	const newState = authReduser(initialState, action);

	// Проверка
	expect(newState.login).toBe('akasha');
	expect(newState.email).toBe('akasha@,ail.ru');
	expect(newState.userID).toBe(1);
	expect(newState.isAuth).toBe(true);
})