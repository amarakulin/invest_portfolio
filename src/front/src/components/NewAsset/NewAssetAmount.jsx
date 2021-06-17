import styled from 'styled-components';
import Input from '../Basic/Input/Input';
import Label from '../Basic/Label/Label';

const NewAssetAmountWrapper = styled.div`
	width: 20%;
`
const NewAssetAmount = ({ input, meta, ...props }) => {
	return (
		<NewAssetAmountWrapper>
			<Label
				htmlFor='amount'
			>
				{props.labelText}
			</Label>
			<Input
				{...input}
				id='amount'
				type='number'
				placeholder='Количество'
				autoComplete='off'
			/>
		</NewAssetAmountWrapper>
	)
}

export default NewAssetAmount;