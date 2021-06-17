import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import Error from '../../Basic/Error/Error';
import { addNewAsset, postNewAssetsData } from '../../../redux/newAssetsReduser';
import { setValue } from '../../../utils/mutators';
import AddedAssets from '../../NewAsset/AddedAssets/AddedAssets';
import { addNewAssetsFormSubmit } from '../../../utils/formSubmit';

const AddNewAssetsForm = (props) => {
	return (
		<Form
			mutators={{setValue}}
			onSubmit={addNewAssetsFormSubmit(props.postNewAssetsData)}
			render={({ handleSubmit, form, submitting, valid, errors, hasSubmitErrors, submitError}) => (
				<form onSubmit={handleSubmit}>
					<NewAsset 
						searchData={props.searchData}
						nessesaryField={props.nessesaryField}
						newAssets={props.newAssets}
						form={form}
					/>
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
	newAssets: state.newAssets.newAssets,
	nessesaryField: state.newAssets.nessesaryField,
	searchData: state.search.searchData,
})

export default connect(mapStateToProps, {addNewAsset, postNewAssetsData})(AddNewAssetsForm);