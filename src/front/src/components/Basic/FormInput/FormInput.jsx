import styled from 'styled-components';
import Input from '../Input/Input';
import Label from '../Label/Label';
import { Field } from 'react-final-form'

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
			>

				{({ input, ...props }) => {
					return (
						<Input
							{ ...input }
							id={props.id}
							placeholder={props.placeholder}
						/>
					);
				}

				}
			</Field>
		</Wrapper>
	);
}

export default FormInput;