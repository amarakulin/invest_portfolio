import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Checkbox from '../../Basic/Checkbox/Checkbox'
import Button from '../../Basic/Button/Button'
import { Wrapper } from '../../Basic/Wrapper/Wrapper'
import Link from '../../Basic/Link/Link'
import { Form } from 'react-final-form'
import { FORM_ERROR } from 'final-form'
import { requiredField } from '../../../utils/validators'
import Preloader from '../../Basic/Preloader/Preloader';
import Error from '../../Basic/Error/Error';
import { login } from '../../../redux/authReduser'
import { connect } from 'react-redux';
import { Redirect } from 'react-router-dom'

const Container = styled.div`
	width: 100%;
	max-width: 500px;
	margin-left: 100px;
`

const AuthForm = (props) => {

	const onSubmit = async (formData) => {
		const error = await props.login(formData.login, formData.password, formData.rememberMe)
		
		if (error)
			return { [FORM_ERROR]: error }
	}

	if (props.isAuth)
		return <Redirect to='/user'/>

	return (
		<Form
			onSubmit={onSubmit}
			render={({ handleSubmit, submitting, pristine, hasSubmitErrors, submitError}) => (
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
						<Wrapper marginBottom={50}>
							<Checkbox
								id="remember"
								labelText="Запомнить меня"
								name="rememberMe"
								type="checkbox"
							/>
							<Link to="/reset">Забыли пароль?</Link>
						</Wrapper>
						{hasSubmitErrors && <Error> {submitError} </Error>}
						<Button disabled={ submitting || pristine }>{submitting ? <Preloader /> : 'Войти'}</Button>
					</form>
				</Container>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	isAuth: state.auth.isAuth
})

export default connect(mapStateToProps, {login})(AuthForm);