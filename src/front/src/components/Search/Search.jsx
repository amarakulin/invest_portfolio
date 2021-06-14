// onChange -> GET request with ?text=${input} to api
import Input from '../Basic/Input/Input';
import SearchResult from './SearchResult'

const res = [
	{
		ticker: 'AXC',
		company: 'APPLE',
		type: 'акция'
	},
	{
		ticker: 'ZKJ',
		company: 'MICROSOFT',
		type: 'акция'
	},
	{
		ticker: 'ANC',
		company: 'ahsjdbajskdnasjahsjdbajskdnasjahsjdbajskdnasjahsjdbajskdnasjahsjdbajskdnasj',
		type: 'акция'
	}
]

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
					input.onChange((e.currentTarget.value ).toUpperCase());

				}}
			/>
			<SearchResult data={res}/>
		</>
	)
}

export default Search;