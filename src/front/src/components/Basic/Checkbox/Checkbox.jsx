import styled from 'styled-components';
import checkboxArrow from '../../../assets/checkbox.svg';
import { Field } from 'react-final-form'

const Checkbox = styled.input.attrs({ type: 'checkbox' })`
	position: absolute;
	z-index: -1;
	opacity: 0;
	& + label {
		display: inline-flex;
		align-items: center;
		user-select: none;
	}
	& + label::before {
		content: '';
		display: inline-block;
		width: 22px;
		height: 22px;
		flex-shrink: 0;
		flex-grow: 0;
		border: 1px solid #adb5bd;
		border-radius: 0.25em;
		margin-right: 0.5em;
		background-repeat: no-repeat;
		background-position: center center;
		background-size: 50% 50%;
	  }
	&:checked+label::before {
		border-color: #74B791;
		background-color: #74B791;
		background-image: url(${checkboxArrow});
	  }
`;

const Label = styled.label`
	cursor: pointer;
	font-weight: normal;
	font-size: 14px;
	line-height: 17px;
	color: #696F79;
`
const CustomCheckbox = ({ input, ...props}) => {

	return (
		<>
			<Checkbox {...input} id={props.id} />
			<Label htmlFor={props.id}>{props.labelText}</Label>
		</>
	)
}

export default CustomCheckbox;