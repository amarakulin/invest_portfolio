import styled from 'styled-components';

const Button = styled.button`
	padding: 21px 118px;
	width: 100%;
	background-color: #89D7AB;
	border: none;
	border-radius: 6px;
	color: #fff;
	transition: background-color 0.2s ease;

	&:hover {
		background-color: #74B791;
	}
	&:disabled {
		background-color: #428860;
	}
`;

export const HeaderButton = styled(Button)`
	font-weight: bold;
	width: auto;
	padding: 13px;
	margin-bottom: 10px;
	position: relative;
	display: flex;
	align-items: center;
	${props => props.marginRight && `margin-right: ${props.marginRight}px`}
`

export const ReversedHeaderButton = styled(HeaderButton)`
	background-color: #fff;
	color: #89D7AB;
	border: 1px solid #89D7AB;
	&:hover {
		color: #fff;
		background-color: #89D7AB;
	}
`

export const AddAsset = styled(HeaderButton)`
	margin-right: 20px;
	padding: 9px;
	&:before {
		content: '+';
		color: #fff;
		font-weight: bold;
		font-size: 2em;
		line-height: 1;
		margin-right: 13px;
	}
`

export const AddAssetButton = styled(AddAsset)`
	margin: 0;
	width: 66px;
	height: 66px;
	padding: 0;
	justify-content: center;
	&:before {
		font-size: 20px;
		margin-right: 0;
	}
`

export const RemoveAssetButton = styled(AddAssetButton)`
	margin: 0;
	&:before {
		content: '-';
	}
`


export default Button;