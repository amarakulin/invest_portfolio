import styled from 'styled-components';
import Input from '../Input/Input';
import Label from '../Label/Label';
import { Field } from 'react-final-form'
import { composeValidators } from '../../../utils/validators'

const Wrapper = styled.div`
	margin-bottom: 25px;
`

const FormInput = (props) => {
	return (
		<Wrapper>
			<Label htmlFor={props.id} > {props.labelText} </Label>
			<Field
				name={props.name}
				placeholder={props.placeholder}
				type={props.type}
				id={props.id}
				validate={composeValidators(...props.validate)}
			>

				{({ input, meta, ...props }) => {
					return (
						<Input
							{ ...input }
							id={props.id}
							placeholder={props.placeholder}
							isError={meta.touched && meta.error}
						/>
					);
				}

				}
			</Field>
		</Wrapper>
	);
}

export default FormInput;