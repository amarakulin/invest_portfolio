import NewAssetFields from '../../NewAsset/NewAssetFields';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import { addNewAsset, postNewAssetsData } from '../../../redux/newAssetsReduser';
import { setValue } from '../../../utils/mutators';
import AddedAssets from '../../NewAsset/AddedAssets/AddedAssets';
import { addNewAssetsFormSubmit } from '../../../utils/formSubmit';
import { updateTotalData } from '../../../redux/assetsReduser';

const AddNewAssetsForm = (props) => {
	return (
		<Form
			mutators={{ setValue }}
			onSubmit={addNewAssetsFormSubmit(props.postNewAssetsData, props.newAssets, props.showAlert, props.updateTotalData)}
			render={({ handleSubmit, form, submitting }) => (
				<form onSubmit={handleSubmit}>
					<NewAssetFields
						searchData={props.searchData}
						nessesaryField={props.nessesaryField}
						newAssets={props.newAssets}
						form={form}
					/>
					{props.newAssets.length ? <AddedAssets data={props.newAssets} /> : null}

					<Button disabled={submitting || !props.newAssets.length}>{submitting ? <Preloader /> : 'Сохранить'}</Button>
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

export default connect(mapStateToProps, { addNewAsset, postNewAssetsData, updateTotalData })(AddNewAssetsForm);