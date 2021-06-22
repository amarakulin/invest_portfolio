import { useState } from 'react';
import Modal from '../Modal/Modal';
import { SmallTitle } from '../Basic/Title/Title';
import { Wrapper } from '../Basic/Wrapper/Wrapper';
import Button from '../Basic/Button/Button';

const Confirm = (props) => {
	const [isModalOpen, toggleIsModal] = useState(true);

	return (
		<Modal closeModal={toggleIsModal} display={isModalOpen}>
			<SmallTitle marginBottom='40'>Подтвердите действие</SmallTitle>
			<Wrapper>
				<Button width='40'>Подтвердить</Button>
				<Button width='40'>Отмена</Button>
			</Wrapper>
		</Modal>
	)
}

export default Confirm;