import { ModalContainer, ModalDialog, CloseModalButton } from './ModalStyles';
import { createPortal } from 'react-dom';

const Modal = (props) => {
	return createPortal(
		<ModalContainer onClick={() => props.closeModal(false)} display={props.display ? 'block' : 'none'} >
			<ModalDialog onClick={e => e.stopPropagation()} >
				<CloseModalButton onClick={() => props.closeModal(false)} >&#10006;</CloseModalButton>
				{props.children}
			</ModalDialog>
		</ModalContainer>,
		document.body
	)
}

export default Modal;