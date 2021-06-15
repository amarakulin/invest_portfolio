import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import AddInput from '../../NewAsset/AddInput';
import { addNewAsset } from '../../../redux/newAssetsReduser'

const AddNewAssetsForm = (props) => {
	const length = props.newAssets.length;


	const onSubmit = async (formData) => {
		console.log(formData);
	}

	return (
		<Form
			mutators={{
				setValue: (args, state, utils) => {
					utils.changeValue(state, args[0], () => args[1])
				}
			}}
			onSubmit={onSubmit}
			render={({ handleSubmit, form, submitting, state }) => (
				<form onSubmit={handleSubmit}>
					{
						props.newAssets.map(el => {
							return el;
						})
					}
					<AddInput 
						elem={ <NewAsset form={form} index={length} key={new Date().getTime()} id={new Date().getTime()} /> } 
						form={form}
					/>
					
					<Button disabled={submitting}>{submitting ? <Preloader /> : 'Сохранить'}</Button>
				</form>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	newAssets: state.newAssets.newAssets
})

export default connect(mapStateToProps, {addNewAsset})(AddNewAssetsForm);