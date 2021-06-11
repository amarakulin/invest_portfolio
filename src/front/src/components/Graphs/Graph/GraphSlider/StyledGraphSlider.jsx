import styled from 'styled-components';

export const GraphSliderContainer = styled.div`
	position: relative;
	margin-bottom: 1rem;
	z-index: 1;
`

const Edge = styled.div`
	transition: background 0.22s ease-in-out;
	background: #f5f9fb;
	opacity: 0.8;
	position: absolute;
	top: 0;
	bottom: 0;
`

export const LeftEdge = styled(Edge)`
	left: 0;
`

export const RightEdge = styled(Edge)`
	right: 0;
`

const Arrow = styled.div`
	position: absolute;
	top: 0;
	bottom: 0;
	transition: background 0.22s ease-in-out;
	background: #ddeaf3;
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

export const Window = styled.div`
	position: absolute;
	background: transparent;
	top: 0;
	bottom: 0;
`