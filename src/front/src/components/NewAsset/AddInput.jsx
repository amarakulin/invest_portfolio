import { increaseNewAssetsNumber } from '../../redux/searchReduser';
import { AddInputStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';
import NewAsset from './NewAsset';

const AddInput = (props) => {
	return (
		<AddInputStyled onClick={(e) => {
			e.preventDefault();
			props.increaseNewAssetsNumber();
		}} />
	)
}

export default connect(null, {increaseNewAssetsNumber})(AddInput);