import { Field } from 'react-final-form';
import { Form } from 'react-final-form';
import { Wrapper } from '../../Basic/Wrapper/Wrapper' 
import styled from 'styled-components';
import sendIcon from '../../../assets/send.png';
import { connect } from 'react-redux';
import { editAsset } from '../../../redux/assetsTableReduser';
import { editAssetAmountFormSubmit } from '../../../utils/formSubmit'

const SendButton = styled.button`
	width: 30%;
	display: block;
	border: none;
	background: transparent url(${sendIcon}) center center / contain no-repeat;
	padding-bottom: 15%;
`

const EditAssetAmountForm = (props) => {
	return (
		<Form
			onSubmit={editAssetAmountFormSubmit({value: props.value, ticker: props.ticker, type:props.type, editAsset: props.editAsset})}
			render={({ handleSubmit, form }) => (
				<form 
					style={{width: '100%'}}
					onSubmit={handleSubmit}
				>
					<Wrapper>
						<Field
							name='amount'
							component='input'
							type='number'
							autoFocus='on'
							style={{maxWidth: '70%'}}
							onBlur={() => {
								props.setEditMode({ticker: null, type: null})
							}}
						>
						</Field>
						<SendButton onMouseDown={form.submit}/>
					</Wrapper>
				</form>
			)}
		/>
	)
}

export default connect(null, {editAsset})(EditAssetAmountForm);