import { useState } from 'react'
import { AlertContainer, AlertText, CloseAlertButton } from './AlertStyles';
import { createPortal } from 'react-dom';
import { connect } from 'react-redux';
import { hideAlert } from '../../redux/alertReduser'


const Alert = (props) => {
	const [className, setClass] = useState('');
	const hideTime = 700;

	const hide = () => {
		setClass('hide');
		setTimeout(() => {
			props.hideAlert();
		}, 700)
	};

	if (!props.type || !props.visible)
		return null

	return createPortal(
		<AlertContainer hideTime={hideTime / 1000} type={props.type} className={className}>
			<AlertText> {props.text} </AlertText>
			<CloseAlertButton onClick={hide}> &#10006; </CloseAlertButton>
		</AlertContainer>,
		document.body
	)
}

const mapStateToProps = (state) => ({
	visible: state.alert.visible,
	type: state.alert.type,
	text: state.alert.text
})

export default connect(mapStateToProps, {hideAlert})(Alert);