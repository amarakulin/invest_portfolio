import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Checkbox from '../../Basic/Checkbox/Checkbox'
import Button from '../../Basic/Button/Button'
import { Wrapper } from '../../Basic/Wrapper/Wrapper'
import Link from '../../Basic/Link/Link'
import { Form, Field } from 'react-final-form'

const Container = styled.div`
	width: 20%;
	margin-left: 150px;
`

const AuthForm = (props) => {
	const onSubmit = (formData) => {
		console.log(formData)
	}
	return (
		<Form
			onSubmit={onSubmit}
			render={({ handleSubmit }) => (
				<Container>
					<form onSubmit={handleSubmit}>
						<Title>Вход</Title>
						<Subtitle>Пожалуйста, заполните все поля</Subtitle>
						<hr></hr>
						<FormInput 
							id="login" 
							placeholder="Введите login"
							labelText="Login*"
							name="login"
							type="text"
						/>
						<FormInput
							id="password"
							placeholder="Введите пароль"
							labelText="Введите пароль*"
							name="password"
							type="password"
						/>
						<Wrapper>
							<Checkbox
								id="remember"
								labelText="Запомнить меня"
								name="rememberMe"
								type="checkbox"
							/>
							<Link to="/reset">Забыли пароль?</Link>
						</Wrapper>
						<Button>Войти</Button>
					</form>
				</Container>
			)}
		/>


		// <Form
		// 	onSubmit={onSubmit}
		// 	render={({ handleSubmit }) => (

		// 	)
		// />
		// <Container>
		// 	<form>
		// 		<Title>Вход</Title>
		// 		<Subtitle>Пожалуйста, заполните все поля</Subtitle>
		// 		<hr></hr>
		// 		<FormInput id="email" placeholder="Введите E-mail" labelText="E-mail*"/>
		// 		<FormInput id="password" placeholder="Введите пароль" labelText="Введите пароль*"/>
		// 		<Wrapper>
		// 			<Checkbox id="remember" text="Запомнить меня"></Checkbox>
		// 			<Link to="/reset">Забыли пароль?</Link>
		// 		</Wrapper>
		// 		<Button>Войти</Button>
		// 	</form>
		// </Container>
	)
}

export default AuthForm;