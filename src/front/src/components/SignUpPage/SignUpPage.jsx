import styled from 'styled-components';
import SignUpForm from './SignUpForm/SignUpForm'
import AuthBG from '../../assets/AuthBG.png'
import {AuthImg} from '../Basic/Img/Img'

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

export default AuthPage;