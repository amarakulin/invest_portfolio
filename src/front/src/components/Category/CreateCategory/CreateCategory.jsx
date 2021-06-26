import Modal from '../../Modal/Modal';
import CreateCategoryForm from '../../Forms/CreateCategoryForm/CreateCategoryForm';

const CreateCategory = (props) => {
	return (
		<Modal isOpen={props.isOpen} close={props.close}>
			<CreateCategoryForm />
		</Modal>
	)
}

export default CreateCategory;