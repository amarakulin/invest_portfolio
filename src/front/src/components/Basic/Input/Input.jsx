import styled from 'styled-components';

const Input = styled.input`
	padding: 23px 20px 24px 30px;
	width: ${props => props.width || '100%'};
	transition: border-color 0.2s ease;
	background: #FFFFFF;
	border: 1px solid #8692A6;
	border-radius: 6px;
	outline: none;
	font-weight: 500;
	font-size: 14px;
	line-height: 17px;
	${props => (props.isError ? 'border: 1px solid tomato;' : "")}

	&:hover,
	&:active,
	&:focus {
		border-color: #74B791;
	}

	&::placeholder {
		color: #8692A6;
	}
`;


export default Input;