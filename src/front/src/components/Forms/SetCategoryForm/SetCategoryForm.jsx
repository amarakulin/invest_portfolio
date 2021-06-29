import { Form, Field } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader';
import Button from '../../Basic/Button/Button';
import Checkbox from '../../Basic/Checkbox/Checkbox';
import { setCategory } from '../../../redux/categoryReduser';
import { SetCategoryFormSubmit } from '../../../utils/formSubmit';
import { SmallTitle } from '../../Basic/Title/Title';
import { setValue } from '../../../utils/mutators';
import { GridWrapper, Wrapper } from '../../Basic/Wrapper/Wrapper';
import { updateTotalData } from '../../../redux/assetsReduser';
import { handleSubmitDecorator } from '../../../utils/formSubmitDecorator';

const SetCategoryForm = (props) => {
	return (
		<Form
			mutators={{ setValue }}
			initialValues={{name: props.settedCategory}}
			onSubmit={SetCategoryFormSubmit(props.setCategory, props.updateTotalData)}
			render={({ handleSubmit, submitting, form }) => (
				<form onSubmit={e => handleSubmitDecorator(handleSubmit, e, form.reset, props.close)()}>
					<SmallTitle marginBottom={40}>Выберите категорию для отображения</SmallTitle>
					<GridWrapper>
						{
							props.categories.length
							? props.categories.map(el => {
								return <Field
									labelText={el}
									name='name'
									type='radio'
									value={el}
									id={el}
									key={el}
								>
									{props => <Checkbox {...props} />}
								</Field>
							})
							: <p>У вас еще нет ни одной категории</p>
						}
					</GridWrapper>

					<Wrapper>
						<Button
							width={45}
							disabled={submitting || !props.categories.length}
							onClick={() => form.mutators.setValue('name', 'total')}
						>
							{submitting ? <Preloader /> : 'Сбросить'}
						</Button>
						<Button width={45} disabled={submitting || !props.categories.length}>{submitting ? <Preloader /> : 'Выбрать'}</Button>
					</Wrapper>
				</form>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	categories: state.category.categories,
	settedCategory: state.category.settedCategory
})

export default connect(mapStateToProps, { setCategory, updateTotalData })(SetCategoryForm);