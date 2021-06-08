import styled from 'styled-components';
import { Container } from '../Basic/Wrapper/Wrapper';
import { connect } from 'react-redux';
import Greeting from './HeaderComponents/Greeting';
import HeaderFooter from './HeaderComponents/HeaderFooter'
import Modal from '../Modal/Modal'
import {openModal, closeModal} from '../../redux/modalReduser'

const StyledHeader = styled.header`
	padding-top: 78px;
	position: relative;
	display: flex;
	&::after {
		content: '';
		position: absolute;
		right: 0%;
		bottom: 0;
		display: block;
		width: 100vw;
		height: 1px;
		background-color: #DADADA;
	}
`

const Header = (props) => {
	return (
		<StyledHeader>
			<Container>
				<Modal closeModal={props.closeModal} display={props.isModalOpen}>
					TEST
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

export default Header;