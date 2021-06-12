import styled from 'styled-components';
import { NavLink } from 'react-router-dom';

const Link = styled(NavLink)`
	
	font-size: 18px;
	line-height: 28px;
	color: #74B791;
	transition: all 0.2s ease;
	&:hover {
		color: #74B791;
	}
`
export const CkeckBoxLink = styled(Link)`
	font-size: 14px;
	line-height: 17px;
`

export const TabLink = styled(NavLink)`
	font-style: normal;
	font-weight: 500;
	font-size: 14px;
	line-height: 17px;
	color: #8692A6;
	display: flex;
	max-width: 120px;
	margin-right: 60px;
	flex-direction: column;
	text-align: center;
	position: relative;
	
	&.${props => props.activeClassName} {
		color: #343A40;
		&:after {
			background-color: #89D7AB;
		}
	}
	
	&:hover {
		text-decoration: none;
		color: #343A40;
	}

	&:after {
		content: '';
		width: 100%;
		height: 3px;
		background-color: #fff;
		dixplay: block;
		margin-top: 18px;
	}

`

export default Link;