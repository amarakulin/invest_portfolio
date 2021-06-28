import { ModalContainer, ModalDialog, CloseModalButton } from './ModalStyles';
import { createPortal } from 'react-dom';

const Modal = ({isOpen, close, ...props}) => {
	return createPortal(
		<ModalContainer onClick={close} isOpen={isOpen} >
			<ModalDialog onClick={e => e.stopPropagation()} >
				<CloseModalButton onClick={close} >&#10006;</CloseModalButton>
				{props.children}
			</ModalDialog>
		</ModalContainer>,
		document.body
	)
}

export default Modal;