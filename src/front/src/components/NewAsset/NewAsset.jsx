import Modal from '../Modal/Modal';
import AddNewAssetsForm from '../Forms/AddNewAssetsForm/AddNewAssetsForm';
import { connect } from 'react-redux';
import { showAlert } from '../../redux/alertReduser';

const NewAsset = (props) => {
	return (
		<>
			<Modal close={props.close} isOpen={props.isOpen}>
				<AddNewAssetsForm showAlert={props.showAlert} />
			</Modal>
		</>
	)
}

export default connect(null, { showAlert })(NewAsset);