import styled from 'styled-components';
import Title from '../../Basic/Title/Title'
import Subtitle from '../../Basic/Subtitle/Subtitle'
import FormInput from '../../Basic/FormInput/FormInput'
import Checkbox from '../../Basic/Checkbox/Checkbox'
import Button from '../../Basic/Button/Button'
import { Wrapper } from '../../Basic/Wrapper/Wrapper'
import { CkeckBoxLink } from '../../Basic/Link/Link'
import { Form } from 'react-final-form'
import { requiredField } from '../../../utils/validators'
import Preloader from '../../Basic/Preloader/Preloader';
import Error from '../../Basic/Error/Error';
import { login } from '../../../redux/authReduser'
import { connect } from 'react-redux';
import { Field } from 'react-final-form'
import { authFormSubmit } from '../../../utils/formSubmit'

import { composeValidators } from '../../../utils/validators'

const Container = styled.div`
	width: 100%;
	max-width: 500px;
	margin-left: 100px;
`

const Fields = [
	{
		id: 'email',
		labelText: 'E-mail*',
		placeholder: 'Введите e-mail',
		name: 'email',
		type: 'text',
		validate: composeValidators(requiredField)
	},
	{
		id: 'password',
		labelText: 'Пароль*',
		placeholder: 'Введите пароль',
		name: 'password',
		type: 'password',
		validate: composeValidators(requiredField)
	}
]

const AuthForm = (props) => {
	return (
		<Form
			onSubmit={authFormSubmit(props.login)}
			render={({ handleSubmit, submitting, pristine, hasSubmitErrors, submitError }) => (
				<Container>
					<form onSubmit={handleSubmit}>
						<Title>Вход</Title>
						<Subtitle>Пожалуйста, заполните все поля</Subtitle>
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
						<Wrapper marginBottom={50}>
							<Field
								id="checkbox"
								name="checkbox"
								type="checkbox"
								labelText="Запомнить меня"
							>
								{({ ...props }) => <Checkbox {...props} />}
							</Field>
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