import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Checkbox from '../../Basic/Checkbox/Checkbox'
import Button from '../../Basic/Button/Button'
import { Wrapper } from '../../Basic/Wrapper/Wrapper'
import { CkeckBoxLink } from '../../Basic/Link/Link'
import { Form } from 'react-final-form'
import { FORM_ERROR } from 'final-form'
import { requiredField, emailValidator } from '../../../utils/validators'
import Preloader from '../../Basic/Preloader/Preloader';
import Error from '../../Basic/Error/Error';
import { login } from '../../../redux/authReduser'
import { connect } from 'react-redux';
import { Field } from 'react-final-form'

import { composeValidators } from '../../../utils/validators'

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

	return (
		<Form
			onSubmit={onSubmit}
			render={({ handleSubmit, submitting, pristine, hasSubmitErrors, submitError }) => (
				<Container>
					<form onSubmit={handleSubmit}>
						<Title>Вход</Title>
						<Subtitle>Пожалуйста, заполните все поля</Subtitle>
						<hr></hr>

						<Field
							id="login"
							labelText="Логин*"
							placeholder="Введите логин"
							name="login"
							type="text"
							validate={composeValidators(requiredField)}
						>
							{ ({ input, meta, ...props }) => <FormInput input={input} meta={meta} {...props} /> }
						</Field>

						<Field
							id="password"
							labelText="Пароль*"
							placeholder="Введите пароль"
							name="password"
							type="password"
							validate={composeValidators(requiredField)}
						>
							{ ({ input, meta, ...props }) => <FormInput input={input} meta={meta} {...props} /> }
						</Field>
						
						<Wrapper marginBottom={50}>
							<Checkbox
								id="remember"
								labelText="Запомнить меня"
								name="rememberMe"
								type="checkbox"
							/>
							<CkeckBoxLink to="/reset">Забыли пароль?</CkeckBoxLink>
						</Wrapper>
						{hasSubmitErrors && <Error> {submitError} </Error>}
						<Button disabled={submitting || pristine}>{submitting ? <Preloader /> : 'Войти'}</Button>
					</form>
				</Container>
			)}
		/>
	)
}

export default connect(null, { login })(AuthForm);