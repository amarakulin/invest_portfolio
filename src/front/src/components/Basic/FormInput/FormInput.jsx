import { useState } from 'react';
import styled from 'styled-components';
import Input from '../Input/Input';
import Label from '../Label/Label';
import { ShowPassword } from './ShowPassword';

const Wrapper = styled.div`
	margin-bottom: 25px;
	position: relative;
`

const FormInput = ({input, meta, ...props }) => {
	let [showPassword, setShowPassword] = useState(false);

	return (
		<Wrapper>
			<Label htmlFor={props.id} > {props.labelText} </Label>
			<Input
				{...input}
				placeholder={props.placeholder}
				type={input.type === 'password' && !showPassword ? 'password' : input.type}
				id={props.id}
				isError={meta.touched && meta.error}
			/>
			{
				input.type === "password" &&
				<ShowPassword onClick={() => setShowPassword(prevState => !prevState)}>
					{showPassword ? 'Скрыть пароль' : 'Показать пароль'}
				</ShowPassword>
			}
		</Wrapper>
	);
}

export default FormInput;