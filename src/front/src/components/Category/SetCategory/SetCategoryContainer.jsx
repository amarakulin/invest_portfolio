import SetCategory from './SetCategory';
import { useState } from 'react';
import { HeaderButton } from '../../Basic/Button/Button';

const SetCategoryContainer = () => {
	const [isOpen, toggleIsOpen] = useState(false);

	return (
		<>
			<HeaderButton
				marginRight={20}
				onClick={() => toggleIsOpen(true)}
			>
				Выбрать категорию
			</HeaderButton>
			<SetCategory isOpen={isOpen} close={() => toggleIsOpen(false)}/>
		</>
	)
}

export default SetCategoryContainer;