import React from 'react';
import {GraphSliderContainer, LeftArrow, RightArrow, windowStyle, LeftEdgeStyle, RightEdgeStyle} from './StyledGraphSlider'
import { GraphSliderCanvas } from '../GraphUtils/GraphStyledUtils';
import { getYRatio, getXRatio, renderLines, calculateBounderies } from '../GraphUtils/utils'
import { connect } from 'react-redux';
import { setDataIndex, widthPercent, setSliderGraphData } from '../../../../redux/graphReduser';



class GraphSlider extends React.Component {
	constructor(props) {
		super(props);
		this.sliderCanvasRef = React.createRef();
		this.data = this.props.data;
	}

	componentDidMount() {
		this.WIDTH = this.props.size.width;
		this.ctx = this.sliderCanvasRef.current.getContext('2d');
		[this.yMin, this.yMax] = calculateBounderies({lines: this.props.data.lines, types: this.props.data.types})

		this.DPI_HEIGHT = this.props.size.height * 2;
		this.DPI_WIDTH = this.props.size.width * 2;
		this.MIN_WIDTH = this.WIDTH * 0.05;
		this.PADDING = this.DPI_HEIGHT * 0.05;

		this.defoultWidth = this.WIDTH - (this.WIDTH * widthPercent / 100);
		this.setPosition(this.defoultWidth, 0);

		this.props.setDataIndex((100 - widthPercent), 100);
		

		this.VIEW_HEIGHT = this.DPI_HEIGHT - this.PADDING * 2;
		this.VIEW_WIDTH = this.DPI_WIDTH;

		this.sliderCanvasRef.current.width = this.DPI_WIDTH;
		this.sliderCanvasRef.current.height = this.DPI_HEIGHT;

		this.yRatio = getYRatio(this.VIEW_HEIGHT, this.yMax, this.yMin);
		this.xRatio = getXRatio(this.VIEW_WIDTH, this.props.data.lines[0].length);

		this.yData = this.props.data.lines.filter(line => this.props.data.types[line[0]] === 'line');
		this.xData = this.props.data.lines.filter(line => this.props.data.types[line[0]] !== 'line')[0];

		document.addEventListener('mouseup', this.mouseUp);

		renderLines(this.ctx, this.yData, this.xRatio, this.yRatio, this.DPI_HEIGHT, this.PADDING, this.data, this.yMin, 0);
	}

	setPosition = (left, right) => {
		const w = this.WIDTH - right - left;

		if (w < this.MIN_WIDTH || left < 0 || right < 0)
			return ;

		this.props.setSliderGraphData({
			windowWidth: w,
			windowLeft: left,
			windowRight: right,
			rightWidth: right,
			leftWidth: left,
		})
	}

	getPosition = () => {
		const left = this.props.posData.leftWidth;
		const right = this.WIDTH - this.props.posData.rightWidth;

		return [
			(left * 100) / this.WIDTH,
			(right * 100) / this.WIDTH,
		]
	}

	mouseDown = (e) => {
		const type = e.target.dataset.type;
		const startX = e.pageX;
		const dimentions = {
			left: this.props.posData.windowLeft,
			right: this.props.posData.windowRight,
			width: this.props.posData.windowWidth
		}

		if (type === 'window') {
			this.grab = (e) => {
				const delta = startX - e.pageX;
			
				if (delta === 0)
					return ;
		
				const left = dimentions.left - delta;
				const right = this.WIDTH - left - dimentions.width;
		
				this.setPosition(left, right);

				const [leftIndex, rightIndex] = this.getPosition();
				this.props.setDataIndex(leftIndex, rightIndex);
			}

			document.addEventListener('mousemove', this.grab)
		} else if (type === 'left' || type === 'right') {
			this.grab = (e) => {
				const delta = startX - e.pageX;
				let left, right;

				if (delta === 0)
					return ;

				if (type === 'left') {
					left = this.WIDTH - (dimentions.width + delta) - dimentions.right;
					right = this.WIDTH - (dimentions.width + delta) - left;

				} else if (type === 'right') {
					right = this.WIDTH - (dimentions.width - delta) - dimentions.left;
					left = dimentions.left;
				}
				
				this.setPosition(left, right);

				const [leftIndex, rightIndex] = this.getPosition();
				this.props.setDataIndex(leftIndex, rightIndex);
			}
			document.addEventListener('mousemove', this.grab);
		}
	}

	mouseUp = () => {
		document.removeEventListener('mousemove', this.grab);
	}


	componentWillUnmount() {
		this.props.setSliderGraphData({
			windowWidth: 0,
			windowLeft: 0,
			windowRight: 0,
			rightWidth: 0,
			leftWidth: 0,
		})

		document.removeEventListener('mousemove', this.grab);
		document.removeEventListener('mouseup', this.mouseUp);
	}

	render() {
		return (
			<GraphSliderContainer onMouseDown={this.mouseDown}>
				<GraphSliderCanvas ref={this.sliderCanvasRef} />
	
				<div
					style={Object.assign({}, 
						LeftEdgeStyle,
						{width: this.props.posData.leftWidth})} 
				>
					<LeftArrow data-type='left'/>
				</div>
	
				<div
					style={Object.assign({},
						windowStyle, {
							left: this.props.posData.windowLeft,
							right: this.props.posData.windowRight,
							width: this.props.posData.windowWidth,
						}
					)}
					data-type='window'
				/>
	
				<div
					style={Object.assign({}, 
						RightEdgeStyle,
						{width: this.props.posData.rightWidth})} 
				>
					<RightArrow data-type='right'/>
				</div>
	
			</GraphSliderContainer>
		)
	}
}

const mapStateToProps = (state) => ({
	posData: state.graph.sliderGraphData
})

export default connect(mapStateToProps, {setDataIndex, setSliderGraphData})(GraphSlider);