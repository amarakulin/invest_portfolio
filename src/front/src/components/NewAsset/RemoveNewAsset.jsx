import { removeNewAsset } from '../../redux/searchReduser';
import { RemoveNewAssetStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const RemoveNewAsset = (props) => {
	return (
		<RemoveNewAssetStyled onClick={(e) => {
			e.preventDefault();
			props.removeNewAssetsNumber();
		}} />
	)
}

export default connect(null, {removeNewAsset})(RemoveNewAsset);