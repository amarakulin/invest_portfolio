import Input from '../Basic/Input/Input';

const NewAssetNumber = ({input, meta, ...props}) => {
	return (
		<Input
			{...input}
			type='number'
			placeholder='Количество'
			width='20%'
			autoComplete='off'
			required
		/>
	)
}

export default NewAssetNumber;