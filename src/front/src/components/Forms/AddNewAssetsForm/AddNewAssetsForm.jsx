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
			onSubmit={onSubmit}
			render={({ handleSubmit, submitting }) => (
				<form onSubmit={handleSubmit}>

					<NewAsset index={1}/>
					<NewAsset index={2}/>
					
					<Button disabled={submitting}>{submitting ? <Preloader /> : 'Сохранить'}</Button>
				</form>
			)}
		/>
	)
}

export default AddNewAssetsForm;