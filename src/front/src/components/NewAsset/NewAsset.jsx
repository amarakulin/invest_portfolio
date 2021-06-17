import styled from 'styled-components';
import { Field } from 'react-final-form';
import Search from '../Search/Search';
import NewAssetNumber from './NewAssetAmount';
import AddNewAsset from './AddNewAsset';
import { validateIdenticalName, amountValidator, validateSearchUnknownTicker, composeValidators } from '../../utils/validators';

const NewAssetContainer = styled.div`
	display: flex;
	align-items: flex-end;
	justify-content: space-between;
	margin-bottom: 25px;
	position: relative;
`

const NewAsset = (props) => {
	return (
		<NewAssetContainer>
			<Field 
				name={'ticker'}
				searchData={props.searchData}
				mutators={props.form.mutators}
				validate={composeValidators(validateIdenticalName(props.newAssets), validateSearchUnknownTicker(props.searchData))}
				nessesaryField={props.nessesaryField}
				labelText='Выберите актив'
			>
				{props => <Search {...props}/>}
			</Field>

			<Field 
				name={'amount'}
				validate={amountValidator}
				labelText='Количество' 
			>
				{props => <NewAssetNumber {...props}/>}
			</Field>
			<AddNewAsset 
				disabled={props.form.getState().invalid}
				values={props.form.getState().values}
				reset={props.form.reset}
			/>
		</NewAssetContainer>
	)
}

export default NewAsset;