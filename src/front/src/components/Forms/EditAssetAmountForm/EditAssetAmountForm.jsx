import { Field } from 'react-final-form';
import { Form } from 'react-final-form';
import { Wrapper } from '../../Basic/Wrapper/Wrapper'
import styled from 'styled-components';
import sendIcon from '../../../assets/send.png';
import { connect } from 'react-redux';
import { editAsset } from '../../../redux/assetsTableReduser';
import { editAssetAmountFormSubmit } from '../../../utils/formSubmit'
import { composeValidators, amountValidator, requiredField } from '../../../utils/validators';
import { updateTotalData } from '../../../redux/assetsReduser';
import { handleSubmitDecorator } from '../../../utils/formSubmitDecorator';

const SendButton = styled.button`
	width: 25%;
	display: block;
	border-radius: 6px;
	border: none;
	height: 30px;
	transition: all 0.2s ease;
	background: transparent url(${sendIcon}) center center / contain no-repeat;
	outline: none;
	${props => props.invalid && 'background-color: rgba(34, 60, 80, 0.2);'};
	&:hover {
		box-shadow: 0px 0px 4px 0px rgba(34, 60, 80, 0.2) inset;
	}
`

const EditAssetAmountForm = (props) => {
	const editAssetSubmitData = {
		value: props.value,
		ticker: props.ticker,
		type: props.type,
		editAsset: props.editAsset
	}
	
	return (
		<Form
			updateTotalData={props.updateTotalData}
			onSubmit={editAssetAmountFormSubmit(editAssetSubmitData, props.showAlert, props.updateTotalData)}
			render={({ handleSubmit, form, invalid }) => (
				<form
					style={{ width: '100%' }}
					onSubmit={e => handleSubmitDecorator(handleSubmit, e)()}
					onBlur={props.resetSelectedAsset}
				>
					<Wrapper>
						<Field
							name='amount'
							component='input'
							type='number'
							autoFocus='on'
							style={{ maxWidth: '70%' }}
							validate={composeValidators(amountValidator, requiredField)}
						/>
						<SendButton disabled={invalid} invalid={invalid} onMouseDown={form.submit} />
					</Wrapper>
				</form>
			)}
		/>
	)
}

export default connect(null, { editAsset, updateTotalData })(EditAssetAmountForm);