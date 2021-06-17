import { useRef, useEffect } from 'react'
import { addNewAsset } from '../../redux/newAssetsReduser';
import { AddAssetButton } from '../Basic/Button/Button'
import { connect } from 'react-redux';

const AddNewAsset = (props) => {
	const buttonRef = useRef(null);

	useEffect(() => {
		buttonRef.current.click();
	}, [])
	return (
		<AddAssetButton
			ref={buttonRef}
			onClick={(e) => {
				e.preventDefault();
				props.addNewAsset(props.elem)
			}} 
		/>
	)
}

export default connect(null, {addNewAsset})(AddNewAsset);