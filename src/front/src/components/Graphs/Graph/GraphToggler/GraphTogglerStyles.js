import styled from 'styled-components';

export const ToggeUl = styled.ul`
	list-style-type: none;
	padding: 0;
	display: flex;
	align-items: center;
	justify-content: center;
`

export const ToggleLi = styled.li`
	margin-right: 15px;
	display: flex;
	align-items: center;
	cursor: pointer;
	font-style: normal;
	font-weight: 500;
	font-size: 14px;
	line-height: 17px;
	color: #8692A6;
	transition: all 0.2s ease;
	&::before {
		content: '';
		display: block;
		width: 15px;
		height: 15px;
		border-radius: 50%;
		background-color: ${props => props.color};
		margin-right: 5px;
	}
	&:hover {
		color: #343A40;
	}
	&.active {
		&::before {
			background-color: #F0F0F0;
		}
	}
`