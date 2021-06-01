import styled from 'styled-components';
import Input from '../Input/Input';
import Label from '../Label/Label';

function FormInput(props) {
	return (
		<>
			<Label for={props.id}>{props.labelText}</Label>
			<Input 
				id={props.id}
				placeholder={props.placeholder}
			></Input>
		</>
	);
}

export default FormInput;