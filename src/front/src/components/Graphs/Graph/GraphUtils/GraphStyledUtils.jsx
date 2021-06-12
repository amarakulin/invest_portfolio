import styled from 'styled-components';

export const GraphCanvas = styled.canvas`
	width: 100%;
	height: 80vh;
`

export const GraphSliderCanvas = styled.canvas`
	width: 100%;
	height: calc(80vh * 0.15);
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