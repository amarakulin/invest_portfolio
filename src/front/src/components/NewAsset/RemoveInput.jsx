import { removeNewAsset } from '../../redux/searchReduser';
import { RemoveInputStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const RemoveInput = (props) => {
	return (
		<RemoveInputStyled onClick={(e) => {
			e.preventDefault();
			props.removeNewAsset();
		}} />
	)
}

export default connect(null, {removeNewAsset})(RemoveInput);