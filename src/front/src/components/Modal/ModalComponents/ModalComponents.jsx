import styled from 'styled-components';

export const ModalContainer = styled.div`
	z-index: 1;
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	width: 100vw;
	height: 100vh;
	background: rgba(0, 0, 0, 0.6);
	display: ${props => props.display}
`
export const ModalDialog = styled.div`
	z-index: 2;
	position: fixed;
	background: #fff;
	border-radius: 6px;
	padding: 60px 20px 30px 20px;
	width: 40%;
	height: auto;
	top:50%;
	left:50%;
	transform: translate(-50%,-50%);
`

export const CloseModalButton = styled.button`
	position: absolute;
	font-size: 22px;
	line-height: 1em;
	top: 20px;
	right: 20px;
	border: none;
	color: #343A40;
	outline: none;
`