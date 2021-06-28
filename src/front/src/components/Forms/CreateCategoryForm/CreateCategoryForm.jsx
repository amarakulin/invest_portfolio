import { Form, Field } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader';
import Button from '../../Basic/Button/Button';
import Checkbox from '../../Basic/Checkbox/Checkbox';
import FormInput from '../../Basic/FormInput/FormInput';
import { createCategory } from '../../../redux/categoryReduser';
import { CreateCategoryFormSubmit } from '../../../utils/formSubmit';
import { showAlert } from '../../../redux/alertReduser';
import { GridWrapper } from '../../Basic/Wrapper/Wrapper';
import { SmallTitle } from '../../Basic/Title/Title';
import { requiredField } from '../../../utils/validators';
import { updateTotalData } from '../../../redux/assetsReduser';

const CreateCategoryForm = (props) => {

	if (!props.totalAssets)
		return null

	return (
		<Form
			onSubmit={CreateCategoryFormSubmit(props.createCategory, props.showAlert, props.updateTotalData)}
			render={({ handleSubmit, submitting, invalid }) => (
				<form onSubmit={handleSubmit}>
					<SmallTitle marginBottom={40}>Выберите активы для новой категории</SmallTitle>
					<GridWrapper>
						{
							props.totalAssets.map(el => {
								return <Field
									labelText={el}
									name={el}
									type='checkbox'
									id={el}
									key={el}
								>
									{props => <Checkbox {...props} />}
								</Field>
							})
						}
					</GridWrapper>
					<Field
						placeholder='Введите название новой категории'
						labelText='Придумайте название новой категории'
						name='name'
						type='text'
						validate={requiredField}
					>
						{props => <FormInput {...props} />}
					</Field>

					<Button disabled={submitting || invalid}>{submitting ? <Preloader /> : 'Создать'}</Button>
				</form>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	totalAssets: state.assets.totalAssets
})

export default connect(mapStateToProps, { createCategory, showAlert, updateTotalData })(CreateCategoryForm);