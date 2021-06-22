import styled from 'styled-components';

const Title = styled.h1`
	text-align: left;
	font-weight: bold;
	font-size: 30px;
	line-height: 36px;
	color: #000000;
	margin: 0;
	margin-bottom: 12px;
`;

export const SmallTitle = styled.h2`
	font-style: normal;
	font-weight: bold;
	font-size: 30px;
	line-height: 36px;
	color: #343A40;
	margin: 0;
	margin-bottom: ${props => props.marginBottom}px;
`


export default Title;