import styled from 'styled-components';
import { Field } from 'react-final-form';
import Search from '../Search/Search';
import NewAssetNumber from './NewAssetAmount';
import RemoveInput from './RemoveInput';
import { requiredField } from '../../utils/validators'

const NewAssetContainer = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 25px;
	position: relative;
`

const NewAsset = (props) => {
	return (
		<NewAssetContainer>
			<Field 
				name={'search-' + props.id}
				mutators={props.form.mutators}
				validate={requiredField}
			>
				{props => <Search {...props} />}
			</Field>

			<Field 
				name={'amount-' + props.id}
				validate={requiredField}
			>
				{props => <NewAssetNumber {...props} />}
			</Field>
			<RemoveInput reset={props.form.change} id={props.id}/>
		</NewAssetContainer>
	)
}

export default NewAsset;