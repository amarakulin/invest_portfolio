import { useState } from 'react';
import Input from '../Basic/Input/Input';
import SearchResult from './SearchResult';
import { getMatchAssets } from '../../redux/searchReduser';
import { connect } from 'react-redux';

const Search = ({ input, meta, ...props }) => {
	const [showSearch, setShowSearch] = useState(false)

	return (
		<>
			<Input
				{...input}
				type='text'
				placeholder='Поиск'
				width='70%'
				autoComplete='off'
				isError={meta.touched && meta.error}
				onBlur={() => {
					input.onBlur();
					setShowSearch(false);
				}}
				onChange={e => {
					const value = (e.currentTarget.value).toUpperCase();

					input.onChange(value);
					props.getMatchAssets(value);
					if (!value.length)
						setShowSearch(false);
					else
						setShowSearch(true);
				}}
			/>
			{showSearch && 
				<SearchResult
					data={props.data}
					isFetching={props.isFetching}
					setShowSearch={setShowSearch}
					setInputData={props.mutators.setValue}
					inputName={input.name}
			/>}
		</>
	)
}

const mapStateToProps = (state) => ({
	isFetching: state.search.isFetching,
	data: state.search.data,
})

export default connect(mapStateToProps, {getMatchAssets})(Search);