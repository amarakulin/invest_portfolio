import { increaseNewAssetsNumber } from '../../redux/searchReduser';
import {AddNewAssset} from '../Basic/Button/Button'
import { connect } from 'react-redux';

const AddNewAsset = (props) => {
	return (
		<AddNewAssset onClick={(e) => {
			e.preventDefault();
			props.increaseNewAssetsNumber();
		}} />
	)
}

export default connect(null, {increaseNewAssetsNumber})(AddNewAsset);