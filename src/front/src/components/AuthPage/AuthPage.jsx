import styled from 'styled-components';
import AuthForm from './AuthForm/AuthForm'
import AuthBG from '../../assets/AuthBG.png'
import {AuthImg} from '../Basic/Img/Img'

const Container = styled.div`
	display: flex;
	align-items: center;
	justify-content: start;
`

const AuthPage = (props) => {
	return (
		<Container>
			<AuthImg src={AuthBG} alt="img" />
			<AuthForm></AuthForm>
		</Container>
	)
}

export default AuthPage;