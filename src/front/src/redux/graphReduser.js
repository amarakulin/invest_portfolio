import { DataAPI } from '../api/api'
import { toggleIsFetching } from './apiReduser'

const SET_DATA = 'SET_DATA';
const RESET_DATA = 'RESET_DATA';
const SET_DATA_INDEX = 'SET_DATA_INDEX';
const SET_SLIDER_DATA = 'SET_SLIDER_DATA';
const SET_TOTAL_GRAPH_DATA = 'SET_TOTAL_GRAPH_DATA';
const SET_HIDDEN_GRAPH_NAME = 'SET_HIDDEN_GRAPH_NAME';
const REMOVE_HIDDEN_GRAPH_NAME = 'REMOVE_HIDDEN_GRAPH_NAME'

const initialState = {
	tooltip: {
		top: null,
		left: null,
		title: '',
		data: []
	},
	showTooltip: false,
	mouseX: null,
	dataIndex: {
		left: null,
		right: null
	},
	sliderGraphData: {
		windowWidth: 0,
		windowLeft: 0,
		windowRight: 0,
		rightWidth: 0,
		leftWidth: 0,
	},
	data: null,
	hiddenGraphsName: []
}

const graphReduser = (state = initialState, action) => {
	switch (action.type) {
		case SET_DATA: {
			return {
				...state,
				tooltip: {
					...action.tooltip
				},
				showTooltip: true,
				mouseX: action.x
			}
		}
		case RESET_DATA: {
			return {
				...state,
				tooltip: {
					top: null,
					left: null,
					title: '',
					data: []
				},
				showTooltip: false,
				mouseX: null
			}
		}
		case SET_DATA_INDEX: {
			return {
				...state,
				dataIndex: {
					...action.index
				}
			}
		}
		case SET_SLIDER_DATA: {
			return {
				...state,
				sliderGraphData: {
					...action.data
				}
			}
		}
		case SET_TOTAL_GRAPH_DATA: {
			return {
				...state,
				data: {
					...action.data
				}
			}
		}
		case SET_HIDDEN_GRAPH_NAME: {
			return {
				...state,
				hiddenGraphsName: [...state.hiddenGraphsName, action.name]
			}
		}
		case REMOVE_HIDDEN_GRAPH_NAME: {
			return {
				...state,
				hiddenGraphsName: state.hiddenGraphsName.filter(el => el !== action.name)
			}
		}
		default: {
			return state;
		}
	}
}

export const setData = ({top, left, title, data, x}) => ({
	type: SET_DATA,
	tooltip: {
		top,
		left,
		title,
		data
	},
	x: x
})

export const resetData = () => ({
	type: RESET_DATA,
});

export const setDataIndex = (left, right) => ({type: SET_DATA_INDEX, index: {left, right}});

export const setSliderGraphData = ({windowWidth, windowLeft, windowRight, rightWidth, leftWidth}) => {
	return {
		type: SET_SLIDER_DATA,
		data: {windowWidth, windowLeft, windowRight, rightWidth, leftWidth}
	}
};

export const setTotalGraphData = (data) => ({type: SET_TOTAL_GRAPH_DATA, data: data});

export const setHiddenGraphName = (name) => ({type: SET_HIDDEN_GRAPH_NAME, name});

export const removeHiddenGraphname = (name) => ({type: REMOVE_HIDDEN_GRAPH_NAME, name})

export const getGraphData = () => (dispatch) => {
	dispatch(toggleIsFetching(true));
	DataAPI.getGraphData()
		.then(res => {
			dispatch(setTotalGraphData(graphDataConverter(res.data)));
			dispatch(toggleIsFetching(false));
		})
		.catch(err => {
			
		})
		.finally(res => {
			dispatch(setTotalGraphData(graphDataConverter(getChartData()))) //! DELETE
			dispatch(toggleIsFetching(false));
		})
}

export default graphReduser;

export const graphDataConverter = (data) => {
	if (data.lines === null)
		return data
	const len = data.lines[0].length;

	const getNewLineData = (line) => {
		const arr = new Array(len - line.length)
			.fill(null)
			.concat(...line.filter((_, i) => i !== 0));
		
		arr.unshift(line[0]);
		return arr;
	}

	data.lines = data.lines.map(line => {
		line = line.map(el => isNaN(parseFloat(el)) ? el : parseFloat(el));

		if (data.types[line[0]] !== 'line')
			return line;
		
		return getNewLineData(line);
	})
	return data;
}

export function getChartData() {
	return {
		lines: [
			[
				'time',
				'1542412800000',
				'1542499200000',
				'1542585600000',
				'1542672000000',
				'1542758400000',
				'1542844800000',
				'1542931200000',
				'1543017600000',
				'1543104000000',
				'1543190400000',
				'1543276800000',
				'1543363200000',
				'1543449600000',
				'1543536000000',
				'1543622400000',
				'1543708800000',
				'1543795200000',
				'1543881600000',
				'1543968000000',
				'1544054400000',
				'1544140800000',
				'1544227200000',
				'1544313600000',
				'1544400000000',
				'1544486400000',
				'1544572800000',
				'1544659200000',
				'1544745600000',
				'1544832000000',
				'1544918400000',
				'1545004800000',
				'1545091200000',
				'1545177600000',
				'1545264000000',
				'1545350400000',
				'1545436800000',
				'1545523200000',
				'1545609600000',
				'1545696000000',
				'1545782400000',
				'1545868800000',
				'1545955200000',
				'1546041600000',
				'1546128000000',
				'1546214400000',
				'1546300800000',
				'1546387200000',
				'1546473600000',
				'1546560000000',
				'1546646400000',
				'1546732800000',
				'1546819200000',
				'1546905600000',
				'1546992000000',
				'1547078400000',
				'1547164800000',
				'1547251200000',
				'1547337600000',
				'1547424000000',
				'1547510400000',
				'1547596800000',
				'1547683200000',
				'1547769600000',
				'1547856000000',
				'1547942400000',
				'1548028800000',
				'1548115200000',
				'1548201600000',
				'1548288000000',
				'1548374400000',
				'1548460800000',
				'1548547200000',
				'1548633600000',
				'1548720000000',
				'1548806400000',
				'1548892800000',
				'1548979200000',
				'1549065600000',
				'1549152000000',
				'1549238400000',
				'1549324800000',
				'1549411200000',
				'1549497600000',
				'1549584000000',
				'1549670400000',
				'1549756800000',
				'1549843200000',
				'1549929600000',
				'1550016000000',
				'1550102400000',
				'1550188800000',
				'1550275200000',
				'1550361600000',
				'1550448000000',
				'1550534400000',
				'1550620800000',
				'1550707200000',
				'1550793600000',
				'1550880000000',
				'1550966400000',
				'1551052800000',
				'1551139200000',
				'1551225600000',
				'1551312000000',
				'1551398400000',
				'1551484800000',
				'1551571200000',
				'1551657600000',
				'1551744000000',
				'1551830400000',
				'1551916800000',
				'1552003200000',
			],
			[
				'y0',
				'51',
				'33',
				'57',
				'75',
				'70',
				'95',
				'70',
				'68',
				'63',
				'66',
				'53',
				'38',
				'10',
				'109',
				'121',
				'53',
				'191',
				'73',
				'87',
				'255',
				'219',
				'170',
				'129',
				'125',
				'126',
				'84',
				'65',
				'53',
				'154',
				'57',
				'71',
				'64',
				'75',
				'72',
				'39',
				'30',
				'52',
				'73',
				'89',
				'156',
				'86',
				'105',
				'88',
				'45',
				'33',
				'56',
				'142',
				'124',
				'114',
				'64',
			],
			[
				'total',
				'22',
				'0',
				'30',
				'436',
				'33',
				'431',
				'18',
				'436',
				'45',
				'0',
				'57',
				'61',
				'70',
				'47',
				'31',
				'34',
				'40',
				'55',
				'27',
				'57',
				'48',
				'32',
				'40',
				'49',
				'54',
				'49',
				'34',
				'51',
				'51',
				'51',
				'66',
				'51',
				'94',
				'60',
				'64',
				'28',
				'44',
				'96',
				'49',
				'73',
				'30',
				'88',
				'63',
				'42',
				'56',
				'67',
				'52',
				'67',
				'35',
				'61',
				'40',
				'55',
				'63',
				'61',
				'105',
				'59',
				'51',
				'76',
				'63',
				'57',
				'47',
				'56',
				'51',
				'98',
				'103',
				'62',
				'54',
				'104',
				'48',
				'41',
				'41',
				'37',
				'30',
				'28',
				'26',
				'37',
				'65',
				'86',
				'70',
				'81',
				'54',
				'74',
				'70',
				'50',
				'74',
				'79',
				'85',
				'62',
				'36',
				'46',
				'68',
				'43',
				'66',
				'50',
				'28',
				'66',
				'39',
				'23',
				'63',
				'74',
				'83',
				'66',
				'40',
				'60',
				'29',
				'336',
				'27',
				'54',
				'89.97',
				'50.45',
				'73.98',
				'52.15',
			],
		],
		types: {
			y0: 'line',
			total: 'line',
			time: 'time',
		},
		names: {
			y0: '#0',
			total: 'total',
		},
		color: {
			y0: '#ffac17',
			total: '#19f3f2'
		},
		purchaseDate: {
			y0: 1547769600000,
			total: 1542412800000
		}
	}
}