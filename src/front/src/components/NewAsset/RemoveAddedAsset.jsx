import { removeNewAsset } from '../../redux/newAssetsReduser';
import { RemoveInputStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const RemoveAddedAsset = (props) => {
	return (
		<RemoveInputStyled onClick={(e) => {
			e.preventDefault();
			props.reset(`search-${props.id}`, undefined);
			props.reset(`amount-${props.id}`, undefined);
			props.removeNewAsset(props.id);
		}} />
	)
}

export default connect(null, {removeNewAsset})(RemoveAddedAsset);