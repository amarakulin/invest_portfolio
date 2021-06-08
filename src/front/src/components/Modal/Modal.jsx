import { ModalContainer, ModalDialog, CloseModalButton } from './ModalComponents/ModalComponents'

const Modal = (props) => {
	return (
		<ModalContainer onClick={props.closeModal} display={props.display} >
			<ModalDialog onClick={e => e.stopPropagation()} >
				<CloseModalButton onClick={props.closeModal} >&#10006;</CloseModalButton>
				{props.children}
			</ModalDialog>
		</ModalContainer>
	)
}

export default Modal;