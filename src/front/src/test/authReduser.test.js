import authReduser, { setUserData } from '../redux/authReduser'

it('123', () => {
	// Исходные данные
	const initialState = {
		login: null,
		email: null,
		userID: null,
		isAuth: localStorage.getItem("isAuth") || false,
	}

	const action = setUserData(1, 'akasha@mail.ru', 'akasha', true);

	// Вызываем reduser
	const newState = authReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({login: 'akasha', email: "akasha@mail.ru", userID: 1, isAuth: true});
})