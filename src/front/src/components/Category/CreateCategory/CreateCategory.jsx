import Modal from '../../Modal/Modal';
import CreateCategoryForm from '../../Forms/CreateCategoryForm/CreateCategoryForm';

const CreateCategory = (props) => {
	return (
		<Modal isOpen={props.isOpen} close={props.close}>
			<CreateCategoryForm close={props.close}/>
		</Modal>
	)
}

export default CreateCategory;