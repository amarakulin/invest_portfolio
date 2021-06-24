import styled from 'styled-components';
import AuthBG from '../../assets/AuthBG.png'
import {AuthImg} from '../Basic/Img/Img'
import { withAuthRedirectToHome } from '../../HOC/withRedirect'

const Container = styled.div`
	display: flex;
	align-items: center;
`

const LoginPage = (props) => {
	return (
		<Container>
			<AuthImg src={AuthBG} alt="img" />
			{props.form}
		</Container>
	)
}

export default withAuthRedirectToHome(LoginPage);