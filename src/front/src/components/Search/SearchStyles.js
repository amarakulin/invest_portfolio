import styled from 'styled-components';

export const SearchResultList = styled.ul`
	padding: 0;
	list-style-type: none;
	position: absolute;
	top: calc(100% + 5px);
	background: #fff;
	border: 1px solid #8692A6;
	z-index: 10;
	width: 70%;
	border-radius: 6px;
	min-height: 50px;
	${props => props.isFetching &&
		`
			display: flex; 
			flex-direction: column;
			align-items: center;
			justify-content: center;
		`
	}
`

export const SearchResultListItem = styled.li`
	cursor: pointer;
	padding: 15px;
	transition: all 0.1s ease;
	display: flex;
	&:first-child {
		border-top-left-radius: 6px;
		border-top-right-radius: 6px;
	}
	&:last-child {
		border-bottom-left-radius: 6px;
		border-bottom-right-radius: 6px;
	}
	&:hover {
		background-color: #f0f3fa;
	}
`

export const SearchResultTicker = styled.strong`
	display: block;
	font-size: 16px;
`

export const SearchResultCompanyName = styled.span`
	max-width: 50%;
	overflow: hidden;
	margin: 0 auto;
	display: block;
	text-overflow: ellipsis;
	white-space: nowrap;
	font-size: 16px;
`

export const SearchResultType = styled.span`
	display: block;
	font-size: 12px;
	color: #8692A6;
`

export const SearchWrapper = styled.div`
	width: 70%;
`