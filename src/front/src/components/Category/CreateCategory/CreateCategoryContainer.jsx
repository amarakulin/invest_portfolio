import { useState } from 'react';
import { HeaderButton } from '../../Basic/Button/Button';
import CreateCategory from './CreateCategory';

const CreateCategoryContainer = () => {
	const [isModalOpen, toggleIsModal] = useState(false);

	return (
		<>
			<HeaderButton
				marginRight={20}
				onClick={() => toggleIsModal(true)}
			>
				Создать категорию
			</HeaderButton>
			<CreateCategory isOpen={isModalOpen} close={() => toggleIsModal(false)}/>
		</>
	)
}

export default CreateCategoryContainer;