import { Container } from '../Basic/Wrapper/Wrapper';
import Greeting from './HeaderComponents/Greeting';
import HeaderFooter from './HeaderComponents/HeaderFooter';
import Modal from '../Modal/Modal';
import {connect} from 'react-redux';
import {logout} from '../../redux/authReduser';
import {openModal, closeModal} from '../../redux/modalReduser';
import AddNewAssetsForm from '../Forms/AddNewAssetsForm/AddNewAssetsForm';
import { StyledHeader } from './HeaderComponents/HeaderStyles';

const Header = (props) => {
	return (
		<StyledHeader>
			<Container>
				<Modal closeModal={props.closeModal} display={props.isModalOpen}>
					<AddNewAssetsForm />
				</Modal>
				<Greeting name={props.name} />
				<HeaderFooter 
					openModal={props.openModal}
					logout={props.logout}
				/>
			</Container>
		</StyledHeader>
	)
}

const mapStateToProps = (state) => ({
	isActive: state.auth.isAuth,
	name: state.auth.name,
	isModalOpen: state.modal.isOpen
})

export default connect(mapStateToProps, {logout, closeModal, openModal})(Header);