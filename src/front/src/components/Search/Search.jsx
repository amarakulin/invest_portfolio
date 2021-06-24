import styled from 'styled-components';
import { useState } from 'react';
import Input from '../Basic/Input/Input';
import SearchResult from './SearchResult';
import { getMatchAssets } from '../../redux/searchReduser';
import { connect } from 'react-redux';
import Label from '../Basic/Label/Label';
import { SearchWrapper } from './SearchStyles';

const onSearchInputChange = (input, setShowSearch, props) => (e) => {
	const value = (e.currentTarget.value).toUpperCase();

	input.onChange(value);
	props.getMatchAssets(value);
	props.searchData.forEach((el, i) => {
		if (el.ticker === value) {
			props.nessesaryField.forEach(el => {
				props.mutators.setValue(el, props.searchData[i][el])
			})
		}
	})
	if (!value)
		setShowSearch(false);
	else
		setShowSearch(true);
}

const onSearchInputBlur = (input, setShowSearch) => () => {
	input.onBlur();
	setShowSearch(false);
}

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
				onBlur={onSearchInputBlur(input, setShowSearch)}
				onChange={onSearchInputChange(input, setShowSearch, props)}
			/>
			{showSearch &&
				<SearchResult
					searchData={props.searchData}
					isFetching={props.isFetching}
					setShowSearch={setShowSearch}
					setInputData={props.mutators.setValue}
					nessesary={props.nessesaryField}
				/>}
		</SearchWrapper>
	)
}

const mapStateToProps = (state) => ({
	isFetching: state.search.isFetching,
})

export default connect(mapStateToProps, { getMatchAssets })(Search);