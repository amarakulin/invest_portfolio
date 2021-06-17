import styled from 'styled-components';

export const GraphCanvas = styled.canvas`
	width: 100%;
	transition: all 0.5s ease;
	height: ${props => props.customHeight || 80}vh;
`

export const GraphSliderCanvas = styled.canvas`
	width: 100%;
	height: 60px;
`

export const GraphContainer = styled.div`
	position: relative;
`

export const GraphPreloaderContainer = styled.div`
	width: 100%;
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 200px;
`