import styled from 'styled-components';
import SignUpForm from './SignUpForm/SignUpForm'
import AuthBG from '../../assets/AuthBG.png'
import {AuthImg} from '../Basic/Img/Img'
import { withAuthRedirectToHome } from '../../HOC/withRedirect'

const Container = styled.div`
	display: flex;
	align-items: center;
`

const AuthPage = (props) => {
	return (
		<Container>
			<AuthImg src={AuthBG} alt="img" />
			<SignUpForm />
		</Container>
	)
}

export default withAuthRedirectToHome(AuthPage);