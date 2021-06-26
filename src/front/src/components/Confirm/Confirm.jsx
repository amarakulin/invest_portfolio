import Modal from '../Modal/Modal';
import { SmallTitle } from '../Basic/Title/Title';
import { Wrapper } from '../Basic/Wrapper/Wrapper';
import Button from '../Basic/Button/Button';

const Confirm = (props) => {
	return (
		<Modal close={props.close} isOpen={props.isOpen}>
			<SmallTitle marginBottom='40'>Подтвердите действие</SmallTitle>
			<Wrapper>
				<Button
					width='40'
					onClick={() => {
						props.confirm();
					}}
				>
					Подтвердить
				</Button>
				<Button
					width='40'
					onClick={() => {
						props.cancel();
					}}
				>
					Отмена
				</Button>
			</Wrapper>
		</Modal>
	)
}

export default Confirm;