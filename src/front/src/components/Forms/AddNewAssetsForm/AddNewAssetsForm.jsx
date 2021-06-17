import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import Error from '../../Basic/Error/Error';
import { newAssetsDataConverter } from '../../../utils/newAssetsDataConverter';
import { addNewAsset, postNewAssetsData } from '../../../redux/newAssetsReduser';
import { setValue } from '../../../utils/mutators';
import { FORM_ERROR } from 'final-form';
import AddedAssets from '../../NewAsset/AddedAssets/AddedAssets'

const AddNewAssetsForm = (props) => {
	const onSubmit = async (data) => {
		const formData = newAssetsDataConverter(data);

		const error = await props.postNewAssetsData(formData);
		
		if (error) {
			return { [FORM_ERROR]: error }
		}
		//TODO после отправки нужно заново запросить данные пользователя
	}

	return (
		<Form
			mutators={{setValue}}
			onSubmit={onSubmit}
			render={({ handleSubmit, form, submitting, valid, errors, hasSubmitErrors, submitError}) => (
				<form onSubmit={handleSubmit}>
					<NewAsset data={props.newAssets} form={form} />
					<AddedAssets data={props.newAssets} />
					
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