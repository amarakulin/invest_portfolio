import styled from 'styled-components';
import { Field } from 'react-final-form';
import Search from '../Search/Search';
import NewAssetNumber from './NewAssetAmount';
import AddNewAsset from './AddAssetButtons/AddNewAsset';
import { validateIdenticalName, amountValidator, validateSearchUnknownTicker, composeValidators, requiredField } from '../../utils/validators';

const NewAssetContainer = styled.div`
	display: flex;
	align-items: flex-end;
	justify-content: space-between;
	margin-bottom: 25px;
	position: relative;
`

const disableAddNewAssetButton = (values, errors) => {//TODO перенести
	if (!values.ticker || !values.name || !values.amount || !values.type)
		return true;
	if (parseInt(values.amount) <= 0)
		return true
	if (errors.ticker)
		return true
	return false;
}

const NewAssetFields = (props) => {
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
				validate={composeValidators(amountValidator)}
				labelText='Количество' 
			>
				{props => <NewAssetNumber {...props}/>}
			</Field>
			<AddNewAsset 
				form={props.form}
				disabled={disableAddNewAssetButton(props.form.getState().values, props.form.getState().errors)}
				values={props.form.getState().values}
				reset={props.form.reset}
			/>
		</NewAssetContainer>
	)
}

export default NewAssetFields;