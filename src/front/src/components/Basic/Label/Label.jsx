import styled from 'styled-components';

const Label = styled.label`
	display: flex;
	align-items: center;
	justify-content: space-between;
	font-weight: 500;
	font-size: 16px;
	line-height: 19px;
	color: #696F79;
	margin-bottom: 8px;
	&::after {
		content: '${props => props.error}';
		display: block;
		color: tomato;
	}
`;


export default Label;