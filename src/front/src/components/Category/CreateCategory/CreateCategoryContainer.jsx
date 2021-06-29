import { useState } from 'react';
import { HeaderButton } from '../../Basic/Button/Button';
import CreateCategory from './CreateCategory';

const CreateCategoryContainer = () => {
	const [isModalOpen, toggleIsOpen] = useState(false);

	return (
		<>
			<HeaderButton
				onClick={() => toggleIsOpen(true)}
			>
				Создать категорию
			</HeaderButton>
			<CreateCategory isOpen={isModalOpen} close={() => toggleIsOpen(false)}/>
		</>
	)
}

export default CreateCategoryContainer;