import { useRef, useEffect } from 'react'
import { addNewAsset } from '../../redux/newAssetsReduser';
import { AddInputStyled } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const AddInput = (props) => {
	const buttonRef = useRef(null);

	useEffect(() => {
		buttonRef.current.click();
	}, [])
	return (
		<AddInputStyled
			ref={buttonRef}
			onClick={(e) => {
				e.preventDefault();
				props.addNewAsset(props.elem)
			}} 
		/>
	)
}

export default connect(null, {addNewAsset})(AddInput);