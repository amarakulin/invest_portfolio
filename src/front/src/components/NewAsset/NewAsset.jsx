import styled from 'styled-components';
import { Field } from 'react-final-form';
import Search from '../Search/Search';
import NewAssetNumber from './NewAssetAmount';
import AddNewAsset from './AddNewAsset';
import { validateIdenticalName } from '../../utils/validators';

const NewAssetContainer = styled.div`
	display: flex;
	align-items: flex-end;
	justify-content: space-between;
	margin-bottom: 25px;
	position: relative;
`

const NewAsset = (props) => {
	const nessesaryField = ['ticker', 'name', 'type', 'amount'];

	return (
		<NewAssetContainer>
			<Field 
				name={'ticker'}
				mutators={props.form.mutators}
				validate={validateIdenticalName(props.data)}
			>
				{props => <Search nessesary={nessesaryField} {...props} labelText='Выберите актив' />}
			</Field>

			<Field 
				name={'amount'}
			>
				{props => <NewAssetNumber labelText='Количество' {...props} />}
			</Field>
			<AddNewAsset 
				disabled={Object.values(props.form.getState().values).length != nessesaryField.length || props.form.getState().invalid}
				values={props.form.getState().values}
				reset={props.form.reset}
			/>
		</NewAssetContainer>
	)
}

export default NewAsset;