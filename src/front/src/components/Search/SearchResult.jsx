import Preloader from '../Basic/Preloader/Preloader';
import { SearchResultList, SearchResultListItem, SearchResultTicker, SearchResultCompanyName, SearchResultType } from './SearchStyles'

const renderSearchResultList = (props) => {
	if (props.isFetching)
		return <Preloader color='black' />
	else {
		if (props.searchData.length) {
			return props.searchData.map((el, i) => {
				return (
					<SearchResultListItem
						onMouseDown={() => {
							props.nessesary.forEach(el => {
								props.setInputData(el, props.searchData[i][el])
							})
							props.setShowSearch(false);
						}}
						key={el.ticker + el.type}
					>
						<SearchResultTicker>{el.ticker.toUpperCase()}</SearchResultTicker>
						<SearchResultCompanyName>{el.name.toUpperCase()}</SearchResultCompanyName>
						<SearchResultType>{el.type}</SearchResultType>
					</SearchResultListItem>
				)
			})
		}
		else
			return <SearchResultListItem>{'Совпадений не найдено'}</SearchResultListItem>
	}
}

const SearchResult = (props) => {
	return (
		<SearchResultList isFetching={props.isFetching}>
			{renderSearchResultList(props)}
		</SearchResultList>
	)
}

export default SearchResult;