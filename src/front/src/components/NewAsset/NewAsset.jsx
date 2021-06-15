import styled from 'styled-components';
import { Field } from 'react-final-form';
import Search from '../Search/Search';
import NewAssetNumber from './NewAssetNumber';
import RemoveInput from './RemoveInput';

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
			<Field name={'search-' + props.id} mutators={props.mutators} >
				{props => <Search {...props} />}
			</Field>

			<Field name={'number-' + props.id} >
				{props => <NewAssetNumber {...props} />}
			</Field>
			<RemoveInput index={props.index} />
		</NewAssetContainer>
	)
}

export default NewAsset;