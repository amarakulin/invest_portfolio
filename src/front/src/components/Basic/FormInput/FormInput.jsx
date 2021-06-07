import { useState } from 'react';
import styled from 'styled-components';
import Input from '../Input/Input';
import Label from '../Label/Label';
import { Field } from 'react-final-form'
import { composeValidators } from '../../../utils/validators'
import { ShowPassword } from './ShowPassword';

const Wrapper = styled.div`
	margin-bottom: 25px;
	position: relative;
`

const FormInput = (props) => {
	let [showPassword, setShowPassword] = useState(false);
	return (
		<Wrapper>
			<Label htmlFor={props.id} > {props.labelText} </Label>
			<Field
				name={props.name}
				placeholder={props.placeholder}
				type={props.type === 'password' && !showPassword ? 'password' : 'text'}
				id={props.id}
				validate={composeValidators(...props.validate)}
			>

				{({ input, meta, ...props }) => {
						return (
							<>
								<Input
									{...input}
									id={props.id}
									placeholder={props.placeholder}
									isError={meta.touched && meta.error}
								/>
							</>
						);
					}
				}
			</Field>
				{
					props.type === "password" &&
						<ShowPassword onClick={() => setShowPassword(prevState => !prevState)}>
							{showPassword ? 'Скрыть пароль' : 'Показать пароль'}
						</ShowPassword>
				}
		</Wrapper>
	);
}

export default FormInput;