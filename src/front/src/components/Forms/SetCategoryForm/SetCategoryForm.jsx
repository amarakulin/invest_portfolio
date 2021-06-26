import { Form, Field } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader';
import Button from '../../Basic/Button/Button';
import Checkbox from '../../Basic/Checkbox/Checkbox';
import { setCategory } from '../../../redux/categoryReduser';
import { SetCategoryFormSubmit } from '../../../utils/formSubmit';
import { SmallTitle } from '../../Basic/Title/Title';

import styled from 'styled-components';

const Wrapper = styled.div`
	display: grid;
	gap: 20px 10px;
	grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
	margin-bottom: 40px;
`

const SetCategoryForm = (props) => {
	return (
		<Form
			onSubmit={SetCategoryFormSubmit(props.setCategory)}
			render={({ handleSubmit, submitting }) => (
				<form onSubmit={handleSubmit}>
					<SmallTitle marginBottom={40}>Выберите категорию для отображения</SmallTitle>
					<Wrapper>
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
					</Wrapper>

					<Button disabled={submitting || !props.categories.length}>{submitting ? <Preloader /> : 'Выбрать'}</Button>
				</form>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	categories: state.category.categories
})

export default connect(mapStateToProps, { setCategory })(SetCategoryForm);