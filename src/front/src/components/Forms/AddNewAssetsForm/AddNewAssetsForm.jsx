import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import { connect } from 'react-redux';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button';
import AddInput from '../../NewAsset/AddInput';
import { addNewAsset, postNewAssetsData } from '../../../redux/newAssetsReduser'

const AddNewAssetsForm = (props) => {
	const onSubmit = async (data) => {
		const arr = Object.values(data);
		const finalArr = [];
		
		for (let i = 0; i < arr.length; i += 2) {
			finalArr.push({
				ticker: arr[i],
				amount: arr[i + 1]
			})
		}

		const formData = {
			value: finalArr
		}

		props.postNewAssetsData(formData);
		//TODO после отправки нужно заново запросить данные пользователя
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
						props.newAssets.map(el => {
							return el;
						})
					}
					<AddInput 
						elem={ <NewAsset form={form} key={new Date().getTime()} id={new Date().getTime()} /> } 
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

export default connect(mapStateToProps, {addNewAsset, postNewAssetsData})(AddNewAssetsForm);