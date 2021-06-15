import { removeNewAsset } from '../../redux/newAssetsReduser';
import { RemoveInputStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const RemoveInput = (props) => {
	return (
		<RemoveInputStyled onClick={(e) => {
			e.preventDefault();
			props.removeNewAsset(props.index);
		}} />
	)
}

export default connect(null, {removeNewAsset})(RemoveInput);