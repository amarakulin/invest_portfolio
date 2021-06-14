import NewAsset from '../../NewAsset/NewAsset';
import { Form } from 'react-final-form';
import Preloader from '../../Basic/Preloader/Preloader'
import Button from '../../Basic/Button/Button'

const AddNewAssetsForm = () => {
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

					<NewAsset mutators={form.mutators} index={1}/>
					
					<Button disabled={submitting}>{submitting ? <Preloader /> : 'Сохранить'}</Button>
				</form>
			)}
		/>
	)
}

export default AddNewAssetsForm;