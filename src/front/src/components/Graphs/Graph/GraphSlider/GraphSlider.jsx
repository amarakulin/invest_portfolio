import { useRef, useEffect } from 'react';
import {GraphSliderContainer, LeftEdge, RightEdge, LeftArrow, RightArrow, Window} from './StyledGraphSlider'
import { GraphSliderCanvas } from '../Canvas';



const toCoords = (y, i, xRatio, yRatio, DPI_HEIGHT, PADDING) => {
	return [
		Math.floor((i - 1) * xRatio),
		Math.floor(DPI_HEIGHT - PADDING - (y * yRatio))
	];
}

const renderLines = ({ctx, yData, xRatio, yRatio, DPI_HEIGHT, PADDING, data}) => {
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


const GraphSlider = ({ size, bounderies, data, ...props }) => {
	const sliderCanvasRef = useRef(null);
	let DPI_HEIGHT, DPI_WIDTH, VIEW_HEIGHT, PADDING, VIEW_WIDTH, yRatio, xRatio, yData, xData;

	useEffect(() => {
		const ctx = sliderCanvasRef.current.getContext('2d');

		initCanvas(ctx);
	}, [])

	const initCanvas = (ctx) => {
		DPI_HEIGHT = size.height * 2;
		DPI_WIDTH = size.width * 2;
		PADDING = DPI_HEIGHT * 0.05;

		VIEW_HEIGHT = DPI_HEIGHT - PADDING * 2;
		VIEW_WIDTH = DPI_WIDTH;

		sliderCanvasRef.current.width = DPI_WIDTH;
		sliderCanvasRef.current.height = DPI_HEIGHT;

		yRatio = VIEW_HEIGHT / (bounderies.yMax - bounderies.yMin);
		xRatio = VIEW_WIDTH / (data.lines[0].length - 2);

		yData = data.lines.filter(line => data.types[line[0]] === 'line')
		xData = data.lines.filter(line => data.types[line[0]] !== 'line')[0]
		debugger
		renderLines({ctx, yData, xRatio, yRatio, DPI_HEIGHT, PADDING, data});
	}

	return (
		<GraphSliderContainer>
			<GraphSliderCanvas ref={sliderCanvasRef} />

			<LeftEdge>
				<LeftArrow />
			</LeftEdge>

			<Window />

			<RightEdge>
				<RightArrow />
			</RightEdge>

		</GraphSliderContainer>
	)
}

export default GraphSlider;