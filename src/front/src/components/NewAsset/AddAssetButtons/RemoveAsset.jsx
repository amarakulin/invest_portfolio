import { RemoveAssetButton } from '../../Basic/Button/Button';
import { connect } from 'react-redux';
import { removeNewAsset } from '../../../redux/newAssetsReduser';

const RemoveAsset = (props) => {
	return (
		<RemoveAssetButton
			onClick={e => {
				e.preventDefault();
				props.removeNewAsset(props.id);
			}}
		/>
	)
}

export default connect(null, {removeNewAsset})(RemoveAsset);