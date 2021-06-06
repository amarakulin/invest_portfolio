import styled from 'styled-components';
import Input from '../Input/Input';
import Label from '../Label/Label';

const Wrapper = styled.div`
	margin-bottom: 25px;
`

const FormInput = (props) => {
	return (
		<Wrapper>
			<Label htmlFor={props.id}>{props.labelText}</Label>
			<Input 
				id={props.id}
				placeholder={props.placeholder}
			></Input>
		</Wrapper>
	);
}

export default FormInput;