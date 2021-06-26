import { Form, Field } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader';
import Button from '../../Basic/Button/Button';
import Checkbox from '../../Basic/Checkbox/Checkbox';
import FormInput from '../../Basic/FormInput/FormInput';
import { createCategory } from '../../../redux/categoryReduser';
import { CreateCategoryFormSubmit } from '../../../utils/formSubmit';
import { showAlert } from '../../../redux/alertReduser';

const CreateCategoryForm = (props) => {

	if (!props.assetsData)
		return null

	return (
		<Form
			onSubmit={CreateCategoryFormSubmit(props.createCategory, props.showAlert)}
			render={({ handleSubmit, submitting }) => (
				<form onSubmit={handleSubmit}>
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