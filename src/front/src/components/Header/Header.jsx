import styled from 'styled-components';
import {Container} from '../Basic/Wrapper/Wrapper';
import Greeting from './HeaderComponents/Greeting';
import { connect } from 'react-redux';

const StledHeader = styled.header`
	padding-top: 78px;
`

const Header = (props) => {
	return (
		<Container>
			<StledHeader>
				<Greeting name={props.name} />
			</StledHeader>
		</Container>
	)
}

const mapStateToProps = (state) => ({
	name: state.auth.login
})

export default connect(mapStateToProps, {})(Header);