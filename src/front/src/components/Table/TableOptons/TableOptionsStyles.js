import styled from "styled-components";
import { CenterWrapper as Wrapper } from '../../Basic/Wrapper/Wrapper';

export const Dots = styled.span`
	position: relative;
	width: 5px;
	height: 5px;
	background-color: #8692A6;
	border-radius: 50%;
	transition: all 0.s ease;
	&:after,
	&:before {
		content: '';
		position: absolute;
		top: 8px;
		display: block;
		width: 5px;
		height: 5px;
		background-color: #8692A6;
		border-radius: 50%;
	}
	&:before {
		top: -8px;
	}
`

export const DotsWrapper = styled(Wrapper)`
	position: relative;
	&:hover ${Dots} {
		background-color: #343A40;
		&:before,
		&:after {
			background-color: #343A40;
		}
	}
`