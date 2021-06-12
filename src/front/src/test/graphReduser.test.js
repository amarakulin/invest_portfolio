import graphReduser, {setData, resetData, setDataIndex, setTotalGraphData} from '../redux/graphReduser.js';

it('Добавление значений через setData', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			top: null,
			left: null,
			title: '',
			data: []
		},
		showTooltip: false,
		mouseX: null
	}

	const data = {
		top: 100,
		left: 200,
		title: 'hello',
		data: [123, 456, 89],
		x: 100,
	}

	const action = setData(data);

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			top: 100,
			left: 200,
			title: 'hello',
			data: [123, 456, 89]
		},
		showTooltip: true,
		mouseX: 100,
	});
})

it('Удаление значений через resetData', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			top: 100,
			left: 200,
			title: 'hello',
			data: [123, 456, 89]
		},
		showTooltip: true,
		mouseX: 100,
	}


	const action = resetData();

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			top: null,
			left: null,
			title: '',
			data: []
		},
		showTooltip: false,
		mouseX: null
	});
})

it('setDataIndex', () => {
	// Исходные данные
	const initialState = {
		dataIndex: {
			left: null,
			right: null
		}
	}


	const action = setDataIndex(10, 20);

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		dataIndex: {
			left: 10,
			right: 20
		}
	});
})

it('setTotalGraphData', () => {
	// Исходные данные
	const initialState = {
		data: null
	}


	const action = setTotalGraphData({
		lines: [
			['time', 10, 20, 30],
			['l1', 10, 20, 3]
		],
		types: {
			time: 'time',
			l1: 'line'
		},
		names: {
			l1: '#1',
		},
		color: {
			l1: '#19f3f2'
		}
	});

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		lines: [
			['time', 10, 20, 30],
			['l1', 10, 20, 3]
		],
		types: {
			time: 'time',
			l1: 'line'
		},
		names: {
			l1: '#1',
		},
		color: {
			l1: '#19f3f2'
		}
	});
})