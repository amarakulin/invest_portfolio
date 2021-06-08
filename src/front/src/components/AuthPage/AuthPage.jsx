import styled from 'styled-components';
import AuthForm from './AuthForm/AuthForm'
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
			<AuthForm />
		</Container>
	)
}

export default withAuthRedirectToHome(AuthPage);