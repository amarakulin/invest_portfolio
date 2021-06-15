import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import AddNewAsset from '../../NewAsset/AddInput'

const AddNewAssetsForm = (props) => {

	const createArr = (form) => {
		console.log(1)
		const arr = []
		for (let i = 0; i < props.newAssetsNumber; i++) {
			arr.push(<NewAsset key={i} mutators={form.mutators} index={i}/>)
		}
		return arr;
	}

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
			render={({ handleSubmit, form, submitting }) => (
				<form onSubmit={handleSubmit}>

					{
						createArr(form).map(el => {
							return el
						})
					}
					<AddNewAsset form={form}/>
					
					<Button disabled={submitting}>{submitting ? <Preloader /> : 'Сохранить'}</Button>
				</form>
			)}
		/>
	)
}

const mapStateToProps = (state) => ({
	newAssetsNumber: state.search.newAssetsNumber
})

export default connect(mapStateToProps, {})(AddNewAssetsForm);