import styled, { keyframes } from "styled-components";

const showAlert = keyframes`
  from {
    transform: translateX(200%);
  }

  to {
    transform: translateX(0);
  }
`;

const hideAlert = keyframes`
  from {
    transform: translateX(0);
  }

  to {
    transform: translateX(200%);
  }
`;


export const AlertContainer = styled.div`
	width: 25%;
	padding: .75rem 1.25rem;
	z-index: 100;
	${props => {
		let color = '';

		switch (props.type) {
			case 'success':
				color = 'A1F9C7';
				break ;
			case 'warning':
				color = 'F8CD7A';
				break ;
			case 'danger':
				color = 'FFA08E';
				break ;
			default: 
				color = 'F8CD7A'
		}

		return `background-color: #${color};`
	}};
	border-radius: 6px;
	position: fixed;
	bottom: 50px;
	right: 50px;
	color: #343A40;
	animation: ${showAlert} 1.2s ease;
	&.hide {
		animation: ${hideAlert} ${props => props.hideTime}s ease;
	}
`

export const AlertText = styled.p`
	font-size: 16px;
	line-height: 24px;
	padding: 0;
	margin: 0;
`

export const CloseAlertButton = styled.button`
	border: none;
	background-color: transparent;
	position: absolute;
	right: 15px;
	top: 10px;
	&:active,
	&:focus {
		outline: none;
	}
`
