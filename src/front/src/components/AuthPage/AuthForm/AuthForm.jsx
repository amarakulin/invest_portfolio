import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Checkbox from '../../Basic/Checkbox/Checkbox'
import Button from '../../Basic/Button/Button'
import {Wrapper} from '../../Basic/Wrapper/Wrapper'
import Link from '../../Basic/Link/Link'

const Container = styled.div`
	width: 20%;
	margin-left: 150px;
`

const AuthForm = (props) => {
	return (
		<Container>
			<Title>Вход</Title>
			<Subtitle>Пожалуйста, заполните все поля</Subtitle>
			<hr></hr>
			<FormInput id="email" placeholder="Введите E-mail" labelText="E-mail*"/>
			<FormInput id="password" placeholder="Введите пароль" labelText="Введите пароль*"/>
			<Wrapper>
				<Checkbox id="remember" text="Запомнить меня"></Checkbox>
				<Link to="/reset">Забыли пароль?</Link>
			</Wrapper>
			<Button>Войти</Button>
		</Container>
	)
}

export default AuthForm;