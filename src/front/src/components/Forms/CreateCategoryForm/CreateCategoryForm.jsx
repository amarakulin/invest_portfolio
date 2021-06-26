import { Form, Field } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader';
import Button from '../../Basic/Button/Button';
import Checkbox from '../../Basic/Checkbox/Checkbox';
import Input from '../../Basic/Input/Input'

const CreateCategoryForm = (props) => {
	
	if (!props.assetsData)
		return null
	
	console.log(props.assetsData)
	return (
		<Form
			onSubmit={(data) => console.log(data)}
			render={({ handleSubmit, submitting }) => (
				<form onSubmit={handleSubmit}>
					{
						props.assetsData.map(el => {
							return <Field
								labelText={el.ticker}
								name={el.ticker}
								id={el.ticker}
								key={el.ticker}
							>
								{props => <Checkbox {...props}/>}
							</Field>
						})
					}
					<Field
						placeholder='Введите название новой категории'
						name={'asd'}
						type="text"
					>
						{props => <Input {...props} />}
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

export default connect(mapStateToProps, {})(CreateCategoryForm);