import { Form, Field } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader';
import Button from '../../Basic/Button/Button';
import Checkbox from '../../Basic/Checkbox/Checkbox';
import FormInput from '../../Basic/FormInput/FormInput';
import { createCategory } from '../../../redux/categoryReduser';
import { CreateCategoryFormSubmit } from '../../../utils/formSubmit';
import { showAlert } from '../../../redux/alertReduser';
import { StartWrapper } from '../../Basic/Wrapper/Wrapper';
import { SmallTitle } from '../../Basic/Title/Title';

import styled from 'styled-components';

const Wrapper = styled.div`
	display: grid;
	gap: 20px 10px;
	grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
	margin-bottom: 40px;
`

const CreateCategoryForm = (props) => {

	if (!props.assetsData)
		return null

	return (
		<Form
			onSubmit={CreateCategoryFormSubmit(props.createCategory, props.showAlert)}
			render={({ handleSubmit, submitting }) => (
				<form onSubmit={handleSubmit}>
					<SmallTitle marginBottom={40}>Выберите активы для новой категории</SmallTitle>
					<Wrapper>
						{
							props.assetsData.map(el => {
								return <Field
									labelText={el.ticker}
									name={el.ticker}
									type='checkbox'
									id={el.ticker}
									key={el.ticker}
								>
									{props => <Checkbox {...props} />}
								</Field>
							})
						}
					</Wrapper>
					<Field
						placeholder='Введите название новой категории'
						labelText='Придумайте название новой категории'
						name='name'
						type='text'
					>
						{props => <FormInput {...props} />}
					</Field>

					<Button disabled={submitting}>{submitting ? <Preloader /> : 'Сохранить'}</Button>
				</form>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	assetsData: state.table.data.body
})

export default connect(mapStateToProps, { createCategory, showAlert })(CreateCategoryForm);