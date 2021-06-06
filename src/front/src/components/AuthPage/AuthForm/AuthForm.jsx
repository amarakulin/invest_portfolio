import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Checkbox from '../../Basic/Checkbox/Checkbox'
import Button from '../../Basic/Button/Button'
import { Wrapper } from '../../Basic/Wrapper/Wrapper'
import Link from '../../Basic/Link/Link'
import { Form } from 'react-final-form'
import { composeValidators, requiredField } from '../../../utils/validators'

const Container = styled.div`
	width: 100%;
	max-width: 500px;
	margin-left: 100px;
`

const AuthForm = (props) => {

	const onSubmit = (formData) => {
		console.log(formData)
	}

	return (
		<Form
			onSubmit={onSubmit}
			render={({ handleSubmit, submitting, valid, ...rest}) => (
				<Container>
					<form onSubmit={handleSubmit}>
						<Title>Вход</Title>
						<Subtitle>Пожалуйста, заполните все поля</Subtitle>
						<hr></hr>
						<FormInput 
							id="login" 
							placeholder="Введите логин"
							labelText="Логин*"
							name="login"
							type="text"
							validate={[requiredField]}

						/>
						<FormInput
							id="password"
							placeholder="Введите пароль"
							labelText="Пароль*"
							name="password"
							type="password"
							validate={[requiredField]}
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
						<Button disabled={ submitting || !valid }>Войти</Button>
					</form>
				</Container>
			)}
		/>
	)
}

export default AuthForm;