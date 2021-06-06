import styled from 'styled-components';

const Button = styled.button`
	padding: 21px 118px;
	width: 100%;
	background-color: #89D7AB;
	border: none;
	border-radius: 6px;
	color: #fff;
	transition: background-color 0.2s ease;

	margin-top: 50px;
	&:hover {
		background-color: #74B791;
	}
	&:disabled {
		background-color: #428860;
	}
`;


export default Button;