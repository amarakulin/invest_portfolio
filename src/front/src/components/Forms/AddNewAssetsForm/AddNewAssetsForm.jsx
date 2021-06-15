import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import AddInput from '../../NewAsset/AddInput';
import Error from '../../Basic/Error/Error';
import { newAssetsDataConverter } from '../../../utils/newAssetsDataConverter';
import { addNewAsset, postNewAssetsData } from '../../../redux/newAssetsReduser';
import { validateIdenticalName } from '../../../utils/validators';
import { setValue } from '../../../utils/mutators';
import { FORM_ERROR } from 'final-form';

const AddNewAssetsForm = (props) => {
	const onSubmit = async (data) => {
		const formData = newAssetsDataConverter(data);
			console.log(formData);
		const error = await props.postNewAssetsData(formData);
		
		if (error) {
			return { [FORM_ERROR]: error }
		}
		//TODO после отправки нужно заново запросить данные пользователя
	}

	return (
		<Form
			validate={validateIdenticalName}
			mutators={{setValue}}
			onSubmit={onSubmit}
			render={({ handleSubmit, form, submitting, valid, errors, hasSubmitErrors, submitError}) => (
				<form onSubmit={handleSubmit}>
					{
						props.newAssets.map(el => {
							return el;
						})
					}
					<AddInput
						elem={ <NewAsset form={form} key={new Date().getTime()} id={new Date().getTime()} /> } 
					/>
					
					{errors.identical && <Error> {errors.identical} </Error>}
					{hasSubmitErrors && <Error> {submitError} </Error>}
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