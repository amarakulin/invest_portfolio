import styled from 'styled-components';

export const TabelBodyCell = styled.div`
	padding: 20px 12px 20px 12px;
	font-style: normal;
	font-weight: normal;
	font-size: 14px;
	color: #272833;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	display: flex;
	align-items: center;
	${props => {
		return props.options && 'justify-content: center; padding: 0 12px;'
	}}
`