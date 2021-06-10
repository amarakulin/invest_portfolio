import graphReduser, {setTooltipData, resetTooltipData, setMouseX, resetMouseX} from '../redux/graphReduser.js';

it('Добавление значений через setTooltipData', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: 10
	}

	const tooltipData = {
		showTooltip: true,
		top: 100,
		left: 200,
		title: 'hello',
		data: [123, 456, 89]
	}
	const action = setTooltipData(tooltipData);

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			showTooltip: true,
			top: 100,
			left: 200,
			title: 'hello',
			data: [123, 456, 89]
		},
		mouseX: 10
	});
})

it('Reset значений через resetTooltipData', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			showTooltip: true,
			top: 100,
			left: 200,
			title: 'hello',
			data: [123, 456, 89]
		},
		mouseX: 10
	}

	const action = resetTooltipData();

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: 10
	});
})

it('set значений через setMouseX, если в mouseX null', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: null
	}

	const action = setMouseX(100);

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: 100
	});
})

it('set значений через setMouseX, если в mouseX не null', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: 50
	}

	const action = setMouseX(100);

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: 100
	});
})

it('reset значений через setMouseX, если в mouseX не null', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: 50
	}

	const action = resetMouseX();

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			showTooltip: false,
			top: null,
			left: null,
			title: '',
			data: []
		},
		mouseX: null
	});
})

it('reset значений через setMouseX, если в tooltip есть данные', () => {
	// Исходные данные
	const initialState = {
		tooltip: {
			showTooltip: true,
			top: 100,
			left: 200,
			title: 'hello',
			data: [123, 456, 89]
		},
		mouseX: 50
	}

	const action = resetMouseX();

	// Вызываем reduser
	const newState = graphReduser(initialState, action);

	// Проверка
	expect(newState).toEqual({
		tooltip: {
			showTooltip: true,
			top: 100,
			left: 200,
			title: 'hello',
			data: [123, 456, 89]
		},
		mouseX: null
	});
})