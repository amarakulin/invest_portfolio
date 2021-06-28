import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Button from '../../Basic/Button/Button'
import { Form, Field } from 'react-final-form'
import Link from '../../Basic/Link/Link'
import { requiredField, emailValidator, validateRepasswordField } from '../../../utils/validators'
import Preloader from '../../Basic/Preloader/Preloader';
import Error from '../../Basic/Error/Error';
import { signUp } from '../../../redux/authReduser'
import { composeValidators } from '../../../utils/validators'
import { connect } from 'react-redux';
import { signUpFormSubmit } from '../../../utils/formSubmit'

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

const Fields = [
	{
		id: 'name',
		labelText: 'Ваше полное имя*',
		placeholder: 'Введите имя',
		name: 'name',
		type: 'text',
		validate: composeValidators(requiredField)
	},
	{
		id: 'email',
		labelText: 'E-mail*',
		placeholder: 'Введите E-mail',
		name: 'email',
		type: 'email',
		validate: composeValidators(requiredField, emailValidator)
	},
	{
		id: 'password',
		labelText: 'Пароль*',
		placeholder: 'Введите пароль',
		name: 'password',
		type: 'password',
		validate: composeValidators(requiredField)
	},
	{
		id: 'rePassword',
		labelText: 'Повторите пароль*',
		placeholder: 'Введите пароль',
		name: 'rePassword',
		type: 'password',
		validate: composeValidators(requiredField)
	}
]

const SignUpForm = (props) => {
	return (
		<Form
			onSubmit={signUpFormSubmit(props.signUp)}
			validate={validateRepasswordField}
			render={({ handleSubmit, submitting, pristine, hasSubmitErrors, submitError }) => (
				<Container>
					<form onSubmit={handleSubmit}>
						<Title>Регистрация</Title>
						<Subtitle>Пожалуйста, заполните все поля отмеченные *</Subtitle>
						<hr></hr>
						{
							Fields.map((el, i) => {
								return (
									<Field
										key={i}
										{...el}
									>
										{({ input, meta, ...props }) => <FormInput input={input} meta={meta} {...props} />}
									</Field>
								)
							})
						}
						{hasSubmitErrors && <Error> {submitError} </Error>}
						<Button disabled={submitting || pristine}>{submitting ? <Preloader /> : 'Зарегистрироваться'}</Button>
						<AlreadyExist>Уже есть аккаунт? <Link to='/login'>Войти</Link></AlreadyExist>
					</form>
				</Container>
			)}
		/>
	)
}

export default connect(null, { signUp })(SignUpForm);