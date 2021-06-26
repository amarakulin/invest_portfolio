import Modal from '../Modal/Modal';
import AddNewAssetsForm from '../Forms/AddNewAssetsForm/AddNewAssetsForm';
import Alert from '../Alert/Alert';
import { connect } from 'react-redux';
import { showAlert } from '../../redux/alertReduser';

const NewAsset = (props) => {
	return (
		<>
			<Modal closeModal={props.closeModal} display={props.display}>
				<AddNewAssetsForm showAlert={props.showAlert} />
			</Modal>
		</>
	)
}

export default connect(null, { showAlert })(NewAsset);