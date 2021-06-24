import styled from 'styled-components';

export const AddedAssetsTitle = styled.h2`
	font-style: normal;
	font-weight: 500;
	font-size: 16px;
	line-height: 19px;
	text-align: center;
	color: #696F79;
`

export const AddedAssetsList = styled.ul`
	padding: 0;
	list-style-type: none;
`

export const AddedAssetsListItem = styled.ul`
	padding: 0;
	list-style-type: none;
	display: grid;
	grid-template-columns: 
		minmax(50px, 25%)
		minmax(50px, 25%)
		minmax(50px, 25%)
		minmax(50px, 20%)
		minmax(50px, 5%)
	;
	align-items: center;
	font-size: 20px;
	line-height: 24px;
	color: #696F79;
	font-weight: 400;
	margin-bottom: 20px;
`

export const AddedAssetsName = styled.span`
	max-width: 90%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
`