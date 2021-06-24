import { ModalContainer, ModalDialog, CloseModalButton } from './ModalStyles'

const Modal = (props) => {
	return (
		<ModalContainer onClick={() => props.closeModal(false)} display={props.display ? 'block' : 'none'} >
			<ModalDialog onClick={e => e.stopPropagation()} >
				<CloseModalButton onClick={() => props.closeModal(false)} >&#10006;</CloseModalButton>
				{props.children}
			</ModalDialog>
		</ModalContainer>
	)
}

export default Modal;