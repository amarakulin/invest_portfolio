import styled from 'styled-components';

export const GraphSliderContainer = styled.div`
	position: relative;
	margin-bottom: 1rem;
	z-index: 1;
`

export const LeftEdgeStyle = {
	transition:'background 0.22s ease-in-out',
	background: '#89D7AB',
	opacity: '0.8',
	position: 'absolute',
	top: '0',
	bottom: '0',
	left: '0',
}

export const RightEdgeStyle = {
	transition:'background 0.22s ease-in-out',
	background: '#89D7AB',
	opacity: '0.8',
	position: 'absolute',
	top: '0',
	bottom: '0',
	right: '0',
}

export const windowStyle = {
	position: 'absolute',
	background: 'transparent',
	top: '0',
	bottom: '0',
	cursor: 'grab'
}

const Arrow = styled.div`
	position: absolute;
	top: 0;
	bottom: 0;
	transition: background 0.22s ease-in-out;
	background: #74B791;
	width: 4px;
`

export const LeftArrow = styled(Arrow)`
	right: 0;
	cursor: w-resize;
`

export const RightArrow = styled(Arrow)`
	left: 0;
	cursor: e-resize;
`