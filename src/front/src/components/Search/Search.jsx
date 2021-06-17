import styled from 'styled-components';
import { useState } from 'react';
import Input from '../Basic/Input/Input';
import SearchResult from './SearchResult';
import { getMatchAssets } from '../../redux/searchReduser';
import { connect } from 'react-redux';
import Label from '../Basic/Label/Label';

const SearchWrapper = styled.div`
	width: 70%;
`

const Search = ({ input, meta, ...props }) => {
	const [showSearch, setShowSearch] = useState(false)

	return (
		<SearchWrapper>
			<Label htmlFor='search' error={meta.touched && meta.error ? meta.error : ''}>
				{props.labelText}
			</Label>
			<Input
				{...input}
				id='search'
				type='text'
				placeholder='Введите название актива'
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
					setShowSearch(true);
				}}
			/>
			{showSearch && 
				<SearchResult
					data={props.data}
					isFetching={props.isFetching}
					setShowSearch={setShowSearch}
					setInputData={props.mutators.setValue}
					nessesary={props.nessesary}
			/>}
		</SearchWrapper>
	)
}

const mapStateToProps = (state) => ({
	isFetching: state.search.isFetching,
	data: state.search.data,
})

export default connect(mapStateToProps, {getMatchAssets})(Search);