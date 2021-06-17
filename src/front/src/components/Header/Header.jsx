import { useState } from 'react';
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
	const [isModalOpen, toggleIsModal] = useState(false);

	return (
		<StyledHeader>
			<Container>
				<Modal closeModal={toggleIsModal} display={isModalOpen}>
					<AddNewAssetsForm />
				</Modal>
				<Greeting name={props.name} />
				<HeaderFooter 
					openModal={toggleIsModal}
					logout={props.logout}
				/>
			</Container>
		</StyledHeader>
	)
}

const mapStateToProps = (state) => ({
	name: state.auth.name
})

export default connect(mapStateToProps, {logout, closeModal, openModal})(Header);