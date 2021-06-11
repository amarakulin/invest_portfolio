import React from 'react';
import {GraphSliderContainer, LeftArrow, RightArrow, windowStyle, LeftEdgeStyle, RightEdgeStyle} from './StyledGraphSlider'
import { GraphSliderCanvas } from '../Canvas';



const toCoords = (y, i, xRatio, yRatio, DPI_HEIGHT, PADDING) => {
	return [
		Math.floor((i - 1) * xRatio),
		Math.floor(DPI_HEIGHT - PADDING - (y * yRatio))
	];
}

const renderLines = (ctx, yData, xRatio, yRatio, DPI_HEIGHT, PADDING, data) => {
	const renderLine = (coords, color = '#000') => {
		ctx.beginPath();
		ctx.lineWidth = 4;
		ctx.strokeStyle = color;
		ctx.lineJoin = 'bevel';
		for (const [x, y] of coords) {
			ctx.lineTo(x, y);
		}
		ctx.stroke();
		ctx.closePath();
	}


	yData.forEach(line => {
		const coords = line.map((y, i) => toCoords(y, i, xRatio, yRatio, DPI_HEIGHT, PADDING)).filter((_, i) => i !== 0);
		
		renderLine(coords, data.color[line[0]]);
	})
}


class GraphSlider extends React.Component {
	constructor(props) {
		super(props);
		this.sliderCanvasRef = React.createRef();
		this.data = this.props.data;

		this.state = {
			windowWidth: 0,
			windowLeft: 0,
			windowRight: 0,
			rightWidth: 0,
			leftWidth: 0,
			cursor: 'grab'
		}

		document.addEventListener('mouseup', this.mouseUp)
	}

	componentDidMount() {
		this.ctx = this.sliderCanvasRef.current.getContext('2d');

		this.WIDTH = this.props.size.width;
		this.HEIGHT = this.props.size.width
		this.DPI_HEIGHT = this.props.size.height * 2;
		this.DPI_WIDTH = this.props.size.width * 2;
		this.MIN_WIDTH = this.WIDTH * 0.05;
		this.PADDING = this.DPI_HEIGHT * 0.05;

		this.defoultWidth = this.WIDTH * 0.3;
		this.setPosition(0, this.WIDTH - this.defoultWidth);

		this.VIEW_HEIGHT = this.DPI_HEIGHT - this.PADDING * 2;
		this.VIEW_WIDTH = this.DPI_WIDTH;

		this.sliderCanvasRef.current.width = this.DPI_WIDTH;
		this.sliderCanvasRef.current.height = this.DPI_HEIGHT;

		this.yRatio = this.VIEW_HEIGHT / (this.props.bounderies.yMax - this.props.bounderies.yMin);
		this.xRatio = this.VIEW_WIDTH / (this.props.data.lines[0].length - 2);

		this.yData = this.props.data.lines.filter(line => this.props.data.types[line[0]] === 'line');
		this.xData = this.props.data.lines.filter(line => this.props.data.types[line[0]] !== 'line')[0];

		renderLines(this.ctx, this.yData, this.xRatio, this.yRatio, this.DPI_HEIGHT, this.PADDING, this.data);
	}

	setPosition = (left, right) => {
		const w = this.WIDTH - right - left;

		if (w < this.MIN_WIDTH) {
			this.windowWidth = this.MIN_WIDTH;
			return ;
		}
		if (left < 0) {
			this.leftPos = 0;
			this.windowWidth = 0;
			return ;
		}

		if (right < 0) {
			this.rightPos = 0;
			this.windowWidth = 0;
			return ;
		}

		this.setState({
			windowWidth: w,
			windowLeft: left,
			windowRight: right,
			rightWidth: right,
			leftWidth: left,
		})
	}

	mouseDown = (e) => {
		const type = e.target.dataset.type;
		const dimentions = {
			left: this.state.windowLeft,
			right: this.state.windowRight,
			width: this.state.windowWidth
		}
		this.setState({
			cursor: 'grabbing'
		})

		if (type === 'window') {
			const startX = e.pageX;

			document.onmousemove = (e) => {
				const delta = startX - e.pageX;
		
				if (delta === 0)
					return ;
		
				const left = dimentions.left - delta;
				const right = this.WIDTH - left - dimentions.width;
		
				this.setPosition(left, right);
			};
		}
	}

	mouseUp = () => {
		document.onmousemove = null;
		this.setState({
			cursor: 'grab'
		})
	}


	componentWillUnmount() {
		this.setState({
			windowWidth: 0,
			windowLeft: 0,
			windowRight: 0,
			rightWidth: 0,
			leftWidth: 0,
			cursor: 'grab'
		});

		document.removeEventListener('mouseup', this.mouseUp);
	}

	render() {
		return (
			<GraphSliderContainer onMouseDown={this.mouseDown}>
				<GraphSliderCanvas ref={this.sliderCanvasRef} />
	
				<div
					style={Object.assign({}, 
						LeftEdgeStyle,
						{left: this.state.leftPos, width: this.state.leftWidth})} 
				>
					<LeftArrow data-type='left'/>
				</div>
	
				<div
					style={Object.assign({},
						windowStyle, {
							left: this.state.windowLeft,
							right: this.state.windowRight,
							width: this.state.windowWidth,
							cursor: this.state.cursor
						}
					)}
					data-type='window'
				/>
	
				<div
					style={Object.assign({}, 
						RightEdgeStyle,
						{left: this.state.rightPos, width: this.state.rightWidth})} 
				>
					<RightArrow data-type='right'/>
				</div>
	
			</GraphSliderContainer>
		)
	}
}

export default GraphSlider;