import React from 'react';
import Tooltip from './Tooltip/Tooltip';
import GraphSlider from './GraphSlider/GraphSlider';
import GraphToggler from './GraphToggler/GraphToggler'
import { getYRatio, getXRatio, toCoords, calculateBounderies, toDate } from './GraphUtils/utils'
import { GraphCanvas, GraphContainer } from './GraphUtils/GraphStyledUtils'

class Graph extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			isMounted: false,
		}
		this.canvasRef = React.createRef();
		this.rows = 12;
		this.raf = null;
		this.tooltipData = [];
	}

	componentDidMount() {
		this.ctx = this.canvasRef.current.getContext('2d');

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

		this.partData = this.props.totalData.lines
		.map(line => {
			const res = line.slice(this.leftIndex, this.rightIndex)
			if (typeof res[0] !== 'string') {
				res.unshift(line[0]);
			}
			return res;
		})
		.filter(line => {
			if (this.props.hiddenName.includes(this.props.totalData.names[line[0]])) {
				return false;
			}
			return true;
		});

		[this.yMin, this.yMax] = calculateBounderies({lines: this.partData, types: this.props.totalData.types});

		this.yRatio = getYRatio(this.VIEW_HEIGHT, this.yMax, this.yMin);
		this.xRatio = getXRatio(this.VIEW_WIDTH, this.partData[0].length);

		this.yData = this.partData.filter(line => this.props.totalData.types[line[0]] === 'line');
		this.xData = this.partData.filter(line => this.props.totalData.types[line[0]] !== 'line')[0];

		if (this.partData.length === 1) {
			this.clear();
			this.renderLine([[0, this.DPI_HEIGHT - this.PADDING], [this.DPI_WIDTH, this.DPI_HEIGHT - this.PADDING]], '#F0F0F0')
			return ;
		}
			
		this.clear();
		this.renderYAxis();
		this.renderXAxis();
		this.renderLines();
	}

	clear = () => {
		this.ctx.clearRect(0, 0, this.DPI_WIDTH, this.DPI_HEIGHT)
	}

	renderLine = (coords, color = '#000') => {
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

	renderLines = () => {
		this.yData.forEach(line => {
			const coords = line.map((y, i) => toCoords(y, i, this.xRatio, this.yRatio, this.DPI_HEIGHT, this.PADDING, this.yMin, this.offsetX)).filter((_, i) => i !== 0);

			this.renderLine(coords, this.props.totalData.color[line[0]]);

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
		// this.raf = requestAnimationFrame(this.paint);
		this.paint();
	}

	mouseLeve = () => {
		this.props.resetData();
	}

	render = () => {
		if (this.state.isMounted)
			this.paint();
		return (
			<GraphContainer>
				<GraphCanvas ref={this.canvasRef} onMouseMove={this.mouseMove} onMouseLeave={this.mouseLeve} />
				{this.props.showTooltip && this.partData.length > 1 && <Tooltip
					data={this.props.tooltip.data || []}
					title={this.props.tooltip.title}
					top={this.props.tooltip.top}
					left={this.props.tooltip.left}
				/>}
				{/* <GraphSlider
					data={this.props.totalData}
				/>
				<GraphToggler 
					data={this.props.totalData}
					setHiddenGraphName={this.props.setHiddenGraphName}
					removeHiddenGraphname={this.props.removeHiddenGraphname}
					hiddenName={this.props.hiddenName}
				/> */}
			</GraphContainer>
		)
	}
}

export default Graph;