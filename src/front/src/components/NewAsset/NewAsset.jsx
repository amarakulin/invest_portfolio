import styled from 'styled-components';
import { Field } from 'react-final-form';
import Search from '../Search/Search';
import NewAssetNumber from './NewAssetNumber';
import AddNewAsset from './AddNewAsset';

const NewAssetContainer = styled.div`
	display: flex;
	align-items: center;
`

const NewAsset = (props) => {
	return (
		<NewAssetContainer>
			<Field name={'search-' + props.index} >
				{props => <Search {...props} />}
			</Field>

			<Field name={'number-' + props.index} >
				{props => <NewAssetNumber {...props} />}
			</Field>
			<AddNewAsset />
		</NewAssetContainer>
	)
}

export default NewAsset;