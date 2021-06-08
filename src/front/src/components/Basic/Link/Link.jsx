import styled from 'styled-components';
import { NavLink } from 'react-router-dom';

const Link = styled(NavLink)`
	
	font-size: 18px;
	line-height: 28px;
	color: #74B791;
	transition: all 02s ease;
	&:hover {
		color: #74B791;
	}
`
export const CkeckBoxLink = styled(Link)`
	font-size: 14px;
	line-height: 17px;
`

export default Link;