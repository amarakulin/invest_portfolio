import Modal from '../../Modal/Modal';
import SetCategoryForm from '../../Forms/SetCategoryForm/SetCategoryForm'


const SetCategory = (props) => {
	return (
		<Modal isOpen={props.isOpen} close={props.close}>
			<SetCategoryForm close={props.close}/>
		</Modal>
	)
}

export default SetCategory;