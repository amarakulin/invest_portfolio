import styled from 'styled-components';

const SearchResultList = styled.ul`
	padding: 0;
	list-style-type: none;
	position: absolute;
	top: calc(100% + 5px);
	background: #fff;
	border: 1px solid #8692A6;
	z-index: 10;
	width: 70%;
	border-radius: 6px;
`

const SearchResultListItem = styled.li`
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

const SearchResultTicker = styled.strong`
	display: block;
`

const SearchResultCompanyName = styled.span`
	max-width: 50%;
	overflow: hidden;
	margin: 0 auto;
	display: block;
	text-overflow: ellipsis;
	white-space: nowrap;
`

const SearchResultType = styled.span`
	display: block;
`

const SearchResult = ({ data }) => {
	return (
		<SearchResultList>
			{
				data.map(el => {
					return (
						<SearchResultListItem key={el.ticker + el.type}>
							<SearchResultTicker>{el.ticker.toUpperCase()}</SearchResultTicker>
							<SearchResultCompanyName>{el.company.toUpperCase()}</SearchResultCompanyName>
							<SearchResultType>{el.type}</SearchResultType>
						</SearchResultListItem>
					)
				})
			}
		</SearchResultList>
	)
}

export default SearchResult;