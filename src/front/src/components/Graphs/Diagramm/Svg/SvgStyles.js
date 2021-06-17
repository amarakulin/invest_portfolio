import styled from 'styled-components';

export const StyledSvg = styled.svg`
	max-height: 400px;
	max-width: 400px;
	min-height: 200px;
	min-width: 200px;
	width: 40%;
	height: 100%;
`

export const Circle = styled.circle`
	fill: none;
	stroke-width: 2;
	cursor: pointer;
	transition: all 0.2s ease;
	&.active,
	&:hover {
		opacity: 0.9;
		stroke-width: 3;
	}
	&.unactive {
		stroke: #E3E3E3;
	}
`

export const SvgText = styled.text`
	font-size: 0.1em;
	color: #8692A6;
`

export const SvgTSpan = styled.tspan`
	text-anchor: middle;
`