import { addNewAsset } from '../../../redux/newAssetsReduser';
import { AddAssetButton } from '../../Basic/Button/Button'
import { connect } from 'react-redux';

const AddNewAsset = (props) => {
	return (
		<AddAssetButton
			disabled={props.disabled}
			onClick={(e) => {
				e.preventDefault();
				props.addNewAsset(props.values);
				for (let field in props.form.getState().dirtyFields) {
					props.form.resetFieldState(field)
				}
				props.reset();
			}} 
		/>
	)
}

export default connect(null, {addNewAsset})(AddNewAsset);