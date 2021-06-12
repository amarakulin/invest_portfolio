import React from 'react';
import Tooltip from './Tooltip/Tooltip';
import GraphSlider from './GraphSlider/GraphSlider';
import Preloader from '../../Basic/Preloader/Preloader'
import { getYRatio, getXRatio, toCoords, calculateBounderies, toDate } from './GraphUtils/utils'
import { connect } from 'react-redux';
import { resetData, setData, setTotalGraphData } from '../../../redux/graphReduser'
import { GraphCanvas, GraphContainer } from './Canvas'

export function getChartData() {
	return {
		lines: [
			[
				'time',
				1542412800000,
				1542499200000,
				1542585600000,
				1542672000000,
				1542758400000,
				1542844800000,
				1542931200000,
				1543017600000,
				1543104000000,
				1543190400000,
				1543276800000,
				1543363200000,
				1543449600000,
				1543536000000,
				1543622400000,
				1543708800000,
				1543795200000,
				1543881600000,
				1543968000000,
				1544054400000,
				1544140800000,
				1544227200000,
				1544313600000,
				1544400000000,
				1544486400000,
				1544572800000,
				1544659200000,
				1544745600000,
				1544832000000,
				1544918400000,
				1545004800000,
				1545091200000,
				1545177600000,
				1545264000000,
				1545350400000,
				1545436800000,
				1545523200000,
				1545609600000,
				1545696000000,
				1545782400000,
				1545868800000,
				1545955200000,
				1546041600000,
				1546128000000,
				1546214400000,
				1546300800000,
				1546387200000,
				1546473600000,
				1546560000000,
				1546646400000,
				1546732800000,
				1546819200000,
				1546905600000,
				1546992000000,
				1547078400000,
				1547164800000,
				1547251200000,
				1547337600000,
				1547424000000,
				1547510400000,
				1547596800000,
				1547683200000,
				1547769600000,
				1547856000000,
				1547942400000,
				1548028800000,
				1548115200000,
				1548201600000,
				1548288000000,
				1548374400000,
				1548460800000,
				1548547200000,
				1548633600000,
				1548720000000,
				1548806400000,
				1548892800000,
				1548979200000,
				1549065600000,
				1549152000000,
				1549238400000,
				1549324800000,
				1549411200000,
				1549497600000,
				1549584000000,
				1549670400000,
				1549756800000,
				1549843200000,
				1549929600000,
				1550016000000,
				1550102400000,
				1550188800000,
				1550275200000,
				1550361600000,
				1550448000000,
				1550534400000,
				1550620800000,
				1550707200000,
				1550793600000,
				1550880000000,
				1550966400000,
				1551052800000,
				1551139200000,
				1551225600000,
				1551312000000,
				1551398400000,
				1551484800000,
				1551571200000,
				1551657600000,
				1551744000000,
				1551830400000,
				1551916800000,
				1552003200000,
			],
			// [
			// 	'y0', 0, 30, 
			// ]
			[
				'y0',
				null,
				null,
				null,
				null,
				32,
				35,
				19,
				65,
				36,
				62,
				113,
				69,
				120,
				60,
				51,
				49,
				71,
				122,
				149,
				69,
				57,
				21,
				33,
				55,
				92,
				62,
				47,
				50,
				56,
				116,
				63,
				60,
				55,
				140,
				76,
				33,
				45,
				64,
				54,
				81,
				180,
				123,
				106,
				37,
				60,
				70,
				46,
				68,
				46,
				51,
				33,
				57,
				75,
				70,
				95,
				70,
				50,
				68,
				63,
				66,
				53,
				38,
				10,
				109,
				121,
				53,
				36,
				71,
				96,
				55,
				58,
				29,
				31,
				55,
				52,
				44,
				126,
				191,
				73,
				87,
				255,
				278,
				219,
				170,
				129,
				125,
				126,
				84,
				65,
				53,
				154,
				57,
				71,
				64,
				75,
				72,
				39,
				30,
				52,
				73,
				89,
				156,
				86,
				105,
				88,
				45,
				33,
				56,
				142,
				124,
				114,
				64,
			],
			[
				'y1',
				22,
				0,
				30,
				40,
				33,
				23,
				18,
				41,
				45,
				0,
				57,
				61,
				70,
				47,
				31,
				34,
				40,
				55,
				27,
				57,
				48,
				32,
				40,
				49,
				54,
				49,
				34,
				51,
				51,
				51,
				66,
				51,
				94,
				60,
				64,
				28,
				44,
				96,
				49,
				73,
				30,
				88,
				63,
				42,
				56,
				67,
				52,
				67,
				35,
				61,
				40,
				55,
				63,
				61,
				105,
				59,
				51,
				76,
				63,
				57,
				47,
				56,
				51,
				98,
				103,
				62,
				54,
				104,
				48,
				41,
				41,
				37,
				30,
				28,
				26,
				37,
				65,
				86,
				70,
				81,
				54,
				74,
				70,
				50,
				74,
				79,
				85,
				62,
				36,
				46,
				68,
				43,
				66,
				50,
				28,
				66,
				39,
				23,
				63,
				74,
				83,
				66,
				40,
				60,
				29,
				336,
				27,
				54,
				89,
				50,
				73,
				52,
			],
		],
		types: {
			y0: 'line',
			y1: 'line',
			time: 'time',
		},
		names: {
			y0: '#0',
			y1: '#1',
		},
		color: {
			y0: '#ffac17',
			y1: '#19f3f2'
		}
	}
}

class Graph extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			isMounted: false,
			// isFetching: true
		}
		this.canvasRef = React.createRef();
		this.props.setTotalGraphData(getChartData());
		// setTimeout(() => {
		// 	this.setState({
		// 		isFetching: false
		// 	})
		// }, 1000);
		this.rows = 12;
		this.raf = null;
		this.tooltipData = [];
	}

	componentDidMount() {
		this.ctx = this.canvasRef.current.getContext('2d');

		this.setState({
			isMounted: true
		})
	}

	componentWillUnmount() {
		cancelAnimationFrame(this.raf);
		this.setState({
			isMounted: false
		})
	}

	paint = () => {
		this.length = this.props.totalData.lines[0].length
		this.leftIndex = Math.floor((this.length * this.props.dataIndex.left) / 100);
		this.rightIndex = Math.ceil((this.length * this.props.dataIndex.right) / 100);

		this.partData = this.props.totalData.lines.map(line => {
			const res = line.slice(this.leftIndex, this.rightIndex)
			if (typeof res[0] !== 'string') {
				res.unshift(line[0]);
			}
			return res;
		});

		[this.yMin, this.yMax] = calculateBounderies({lines: this.partData, types: this.props.totalData.types});

		this.WIDTH = this.canvasRef.current.offsetWidth;
		this.HEIGHT = this.canvasRef.current.offsetHeight;

		this.DPI_WIDTH = this.WIDTH * 2;
		this.DPI_HEIGHT = this.HEIGHT * 2;

		this.canvasRef.current.width = this.DPI_WIDTH;
		this.canvasRef.current.height = this.DPI_HEIGHT;

		this.offsetX = this.DPI_WIDTH * 0.05;
		this.PADDING = this.DPI_HEIGHT * 0.05;

		this.VIEW_HEIGHT = this.DPI_HEIGHT - this.PADDING * 2;
		this.VIEW_WIDTH = this.DPI_WIDTH - this.offsetX;

		this.yRatio = getYRatio(this.VIEW_HEIGHT, this.yMax, this.yMin);
		this.xRatio = getXRatio(this.VIEW_WIDTH, this.partData[0].length);

		this.yData = this.partData.filter(line => this.props.totalData.types[line[0]] === 'line');
		this.xData = this.partData.filter(line => this.props.totalData.types[line[0]] !== 'line')[0];

		this.clear();
		this.renderYAxis();
		this.renderXAxis();
		this.renderLines();
	}

	clear = () => {
		this.ctx.clearRect(0, 0, this.DPI_WIDTH, this.DPI_HEIGHT)
	}

	renderLines = () => {
		const renderLine = (coords, color = '#000') => {
			this.ctx.beginPath();
			this.ctx.lineWidth = 4;
			this.ctx.strokeStyle = color;
			this.ctx.lineJoin = 'bevel';
			for (const [x, y] of coords) {
				if (y === null)
					continue;
				this.ctx.lineTo(x, y);
			}
			this.ctx.stroke();
			this.ctx.closePath();
		}

		this.yData.forEach(line => {
			const coords = line.map((y, i) => toCoords(y, i, this.xRatio, this.yRatio, this.DPI_HEIGHT, this.PADDING, this.yMin, this.offsetX)).filter((_, i) => i !== 0);

			renderLine(coords, this.props.totalData.color[line[0]]);

			for (const [x, y] of coords) {
				if (this.isOver(x - this.offsetX, coords.length)) {
					this.circle(x, y, this.props.totalData.color[line[0]]);
					break;
				}

			}
		})
	}

	renderYAxis = () => {
		const step = Math.floor(this.VIEW_HEIGHT / this.rows);
		const textStep = (this.yMax - this.yMin) / this.rows;

		this.ctx.beginPath();
		this.ctx.lineWidth = 2;
		this.ctx.strokeStyle = '#F0F0F0';

		this.ctx.font = 'normal 32px Inter, sans-serif';
		this.ctx.fillStyle = '#8692A6';

		for (let i = 1; i <= this.rows; i++) {
			const y = i * step;
			const text = this.yMax - textStep * i;

			this.ctx.fillText(Math.floor(text), 0, y + this.PADDING);

			this.ctx.moveTo(this.offsetX, y + this.PADDING);
			this.ctx.lineTo(this.DPI_WIDTH, y + this.PADDING);
		}
		this.ctx.stroke();
		this.ctx.closePath();
	}

	renderXAxis = () => {
		const colCount = 6;
		const step = Math.round(this.xData.length / colCount);

		this.ctx.beginPath();

		for (let i = 1; i < this.xData.length; i++) {
			const x = i * this.xRatio;

			if ((i - 1) % step === 0) {
				const text = toDate(this.xData[i]);
				this.ctx.fillText(text, x, this.DPI_HEIGHT - 10);
				this.ctx.moveTo(x + this.offsetX, 0 + this.PADDING * 2);
				this.ctx.lineTo(x + this.offsetX, this.DPI_HEIGHT - this.PADDING);
			}


			if (this.isOver(x, this.xData.length)) {
				this.ctx.save();

				this.ctx.moveTo(x + this.offsetX, this.PADDING);
				this.ctx.lineTo(x + this.offsetX, this.DPI_HEIGHT - this.PADDING)

				this.ctx.restore();

				this.tooltipTitle = toDate(this.xData[i]);


				this.tooltipData = this.yData.map(line => ({
					value: line[i + 1],
					name: this.props.totalData.names[line[0]],
					color: this.props.totalData.color[line[0]]
				}))
			}
		}

		this.ctx.stroke();
		this.ctx.closePath();
	}

	isOver = (x, length) => {
		if (!this.props.mouseX)
			return false;
		const width = this.DPI_WIDTH / length;

		return Math.abs((x - this.props.mouseX)) < width / 2;
	}

	circle = (x, y, color = '#000') => {
		this.ctx.beginPath();

		this.ctx.strokeStyle = color;
		this.ctx.fillStyle = '#fff';

		this.ctx.arc(x, y, 10, 0, Math.PI * 2)
		this.ctx.fill();
		this.ctx.stroke();

		this.ctx.closePath();
	}

	mouseMove = ({ clientX, clientY }) => {
		const { left, top } = this.canvasRef.current.getBoundingClientRect();

		this.props.setData({
			top: clientY - top,
			left: clientX - left + this.offsetX / 3,
			title: this.tooltipTitle,
			data: this.tooltipData,
			x: (clientX - left) * 2 - this.offsetX
		});
		this.raf = requestAnimationFrame(this.paint);
	}

	mouseLeve = () => {
		this.props.resetData();
	}

	render = () => {
		// if (this.state.isFetching)
		// 	return <Preloader color='black'/>
		if (this.state.isMounted)
			this.paint();
		return (
			<GraphContainer>
				<GraphCanvas ref={this.canvasRef} onMouseMove={this.mouseMove} onMouseLeave={this.mouseLeve} />
				{this.props.showTooltip && <Tooltip
					data={this.props.tooltip.data || []}
					title={this.props.tooltip.title}
					top={this.props.tooltip.top}
					left={this.props.tooltip.left}
				/>}
				{this.state.isMounted && 
					<GraphSlider
						data={this.props.totalData}
						size={{
							height: this.HEIGHT * 0.1,
							width: this.WIDTH,
						}}
					/>
				}

			</GraphContainer>
		)
	}
}

const mapDispatchToProps = (state) => ({
	tooltip: state.graph.tooltip,
	mouseX: state.graph.mouseX,
	showTooltip: state.graph.showTooltip,
	dataIndex: state.graph.dataIndex,
	totalData: state.graph.data,
})

export default connect(mapDispatchToProps, { resetData, setData, setTotalGraphData })(Graph);