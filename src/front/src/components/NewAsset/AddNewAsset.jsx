import { increaseNewAssetsNumber } from '../../redux/searchReduser';
import { AddNewAssetStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const AddNewAsset = (props) => {
	return (
		<AddNewAssetStyled onClick={(e) => {
			e.preventDefault();
			props.increaseNewAssetsNumber();
		}} />
	)
}

export default connect(null, {increaseNewAssetsNumber})(AddNewAsset);