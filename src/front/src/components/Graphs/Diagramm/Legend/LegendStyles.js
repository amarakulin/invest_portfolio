import styled from 'styled-components';

export const LegendContainer = styled.div`
	width: 40%;
`

export const LegenTitle = styled.h3`
	font-style: normal;
	font-weight: bold;
	font-size: 30px;
	line-height: 36px;
	color: #343A40;
	margin: 0 0  43px 0;
`

export const LegendList = styled.ul`
	padding: 0;
	list-style-type: none;
	display: inline-block;
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
	max-height: 400px;
`

export const LegendListItem = styled.li`
	font-style: normal;
	font-weight: normal;
	font-size: 18px;
	line-height: 22px;
	color: #8692A6;
	margin-bottom: 25px;
	display: flex;
	align-items: center;
	cursor: pointer;
	
	&::before {
		content: '';
		transition: all 0.2s ease;
		width: 25px;
		height: 25px;
		margin-right: 10px;
		display: inline-block;
		background-color: ${props => props.color};
	}
	&.unactive {
		&::before {
			background-color: #E3E3E3;
		}
	}
`