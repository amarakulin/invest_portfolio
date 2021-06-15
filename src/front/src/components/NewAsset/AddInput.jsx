import { addNewAsset } from '../../redux/newAssetsReduser';
import { AddInputStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const AddInput = (props) => {
	return (
		<AddInputStyled 
			disabled={props.disabled}
			onClick={(e) => {
				e.preventDefault();
				props.addNewAsset(props.elem)
			}} 
		/>
	)
}

export default connect(null, {addNewAsset})(AddInput);