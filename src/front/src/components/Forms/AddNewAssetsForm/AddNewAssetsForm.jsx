import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import AddInput from '../../NewAsset/AddInput';
import Error from '../../Basic/Error/Error';
import { newAssetsDataConverter } from '../../../utils/newAssetsDataConverter'
import { addNewAsset, postNewAssetsData } from '../../../redux/newAssetsReduser'
import { validateIdenticalName } from '../../../utils/validators'
import { setValue } from '../../../utils/mutators'

const AddNewAssetsForm = (props) => {
	const onSubmit = async (data) => {
		const formData = newAssetsDataConverter(data);

		props.postNewAssetsData(formData); //TODO добавить обработку ошибок
		//TODO после отправки нужно заново запросить данные пользователя
	}

	return (
		<Form
			validate={validateIdenticalName}
			mutators={{setValue}}
			onSubmit={onSubmit}
			render={({ handleSubmit, form, submitting, valid, errors }) => (
				<form onSubmit={handleSubmit}>
					{
						props.newAssets.map(el => {
							return el;
						})
					}
					<AddInput
						disabled={!valid}
						elem={ <NewAsset form={form} key={new Date().getTime()} id={new Date().getTime()} /> } 
					/>
					
					{errors.identical && <Error> {errors.identical} </Error>}
					<Button disabled={submitting || !valid}>{submitting ? <Preloader /> : 'Сохранить'}</Button>
				</form>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	newAssets: state.newAssets.newAssets
})

export default connect(mapStateToProps, {addNewAsset, postNewAssetsData})(AddNewAssetsForm);