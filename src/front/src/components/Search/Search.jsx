import Input from '../Basic/Input/Input';
import SearchResult from './SearchResult';
import { getMatchAssets, setShowSearch } from '../../redux/searchReduser';
import { connect } from 'react-redux';

const Search = ({ input, meta, ...props }) => {
	return (
		<>
			<Input
				{...input}
				type='text'
				placeholder='Поиск'
				width='70%'
				autoComplete='off'
				onChange={e => {
					const value = (e.currentTarget.value).toUpperCase();

					input.onChange(value);
					props.getMatchAssets(value);
					if (!value.length)
						props.setShowSearch(false);
					else
						props.setShowSearch(true);
				}}
			/>
			{props.showSearch && <SearchResult data={props.data} isFetching={props.isFetching}/>}
		</>
	)
}

const mapStateToProps = (state) => ({
	isFetching: state.search.isFetching,
	data: state.search.data,
	showSearch: state.search.showSearch
})

export default connect(mapStateToProps, {getMatchAssets, setShowSearch})(Search);