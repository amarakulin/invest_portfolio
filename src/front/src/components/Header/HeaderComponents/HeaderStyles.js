import styled from 'styled-components';
import {SmallTitle} from '../../Basic/Title/Title'

export const ButtonWrapper = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
`

export const Name = styled(SmallTitle)`
	margin: 0 0 0 36px;
`

export const StyledHeader = styled.header`
	padding-top: 78px;
	position: relative;
	display: flex;
	margin-bottom: 50px;
	&::after {
		content: '';
		position: absolute;
		right: 0%;
		bottom: 0;
		display: block;
		width: 100vw;
		height: 1px;
		background-color: #DADADA;
	}
`