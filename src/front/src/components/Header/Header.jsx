import { useState } from 'react';
import { Container } from '../Basic/Wrapper/Wrapper';
import Greeting from './HeaderComponents/Greeting';
import HeaderFooter from './HeaderComponents/HeaderFooter';
import {connect} from 'react-redux';
import {logout} from '../../redux/authReduser';
import { StyledHeader } from './HeaderComponents/HeaderStyles';
import NewAsset from '../NewAsset/NewAsset';

const Header = (props) => {
	const [isModalOpen, toggleIsModal] = useState(false);

	return (
		<StyledHeader>
			<Container>
				<NewAsset closeModal={toggleIsModal} display={isModalOpen}/>
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

export default connect(mapStateToProps, {logout})(Header);