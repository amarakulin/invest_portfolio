import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Button from '../../Basic/Button/Button'
import Link from '../../Basic/Link/Link'
import { Form } from 'react-final-form'
import { FORM_ERROR } from 'final-form'
import { requiredField, emailValidator } from '../../../utils/validators'
import Preloader from '../../Basic/Preloader/Preloader';
import Error from '../../Basic/Error/Error';
import { login } from '../../../redux/authReduser'
import { connect } from 'react-redux';

const Container = styled.div`
	width: 100%;
	max-width: 500px;
	margin-left: 100px;
`

const AlreadyExist = styled.h2`
	font-weight: normal;
	font-size: 18px;
	line-height: 28px;
	color: #8692A6;
`

const AuthForm = (props) => {

	const onSubmit = async (formData) => {
		// const error = await props.login(formData.login, formData.password, formData.rememberMe)
		
		// if (error)
		// 	return { [FORM_ERROR]: error }
	}

	return (
		<Form
			onSubmit={onSubmit}
			render={({ handleSubmit, submitting, pristine, hasSubmitErrors, submitError}) => (
				<Container>
					<form onSubmit={handleSubmit}>
						<Title>Регистрация</Title>
						<Subtitle>Пожалуйста, заполните все поля отмеченные *</Subtitle>
						<hr></hr>
						<FormInput 
							id="name" 
							labelText="Ваше полное имя*"
							placeholder="Введите имя"
							name="name"
							type="text"
							validate={[requiredField]}
						/>
						<FormInput
							id="email"
							labelText="E-mail*"
							placeholder="Введите E-mail"
							name="email"
							type="email"
							validate={[requiredField, emailValidator]}
						/>
						<FormInput
							id="password"
							labelText="Придумайте пароль*"
							placeholder="Введите пароль"
							name="password"
							type="password"
							validate={[requiredField]}
						/>
						<FormInput
							id="re-password"
							labelText="Повторите пароль*"
							placeholder="Введите пароль"
							name="re-password"
							type="password"
							validate={[requiredField]}
						/>
						{hasSubmitErrors && <Error> {submitError} </Error>}
						<Button disabled={ submitting || pristine }>{submitting ? <Preloader /> : 'Войти'}</Button>
						<AlreadyExist>Уже есть аккаунт? <Link to='/login'>Войти</Link></AlreadyExist>
					</form>
				</Container>
			)}
		/>
	)
}

export default connect(null, {})(AuthForm);