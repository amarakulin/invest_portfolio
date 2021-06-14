// onChange -> GET request with ?text=${input} to api
import Input from '../Basic/Input/Input';

const Search = ({input, meta, ...props}) => {
	return (
		<Input
			{...input}
			type='text'
			placeholder='Поиск'
			onChange={(e) => {
				input.onChange(e);
			}}
		/>
	)
}

export default Search;