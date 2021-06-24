import styled from 'styled-components';

export const OptionsDropdownList = styled.ul`
	position: absolute;
	padding: 0;
	z-index: 10;
	top: 50%;
	background-color: #fff;
	border-radius: 6px;
	list-style-type: none;
	display: ${props => props.isOpen ? 'block' : 'none'};
`

export const OptionsDropdownItem = styled.li`
	font-style: normal;
	font-weight: normal;
	font-size: 14px;
	color: #272833;
	padding: 10px 15px;
	&:hover {
		background-color: #F3F3FB;
	}
	&:first-child {
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
	}
	&:last-child {
		border-bottom-left-radius: 6px;
		border-bottom-right-radius: 6px;
	}
`