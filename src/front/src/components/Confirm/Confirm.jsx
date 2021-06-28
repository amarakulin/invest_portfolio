import Modal from '../Modal/Modal';
import { SmallTitle } from '../Basic/Title/Title';
import { Wrapper } from '../Basic/Wrapper/Wrapper';
import Button from '../Basic/Button/Button';
import { connect } from 'react-redux';
import { closeConfirm } from '../../redux/confirmReduser'

const Confirm = (props) => {
	return (
		<Modal close={props.closeConfirm} isOpen={props.isOpen}>
			<SmallTitle marginBottom='40'>Подтвердите действие</SmallTitle>
			<Wrapper>
				<Button
					width='40'
					onClick={() => {
						props.confirmCallback();
						props.closeConfirm();
					}}
				>
					Подтвердить
				</Button>
				<Button
					width='40'
					onClick={props.closeConfirm}
				>
					Отмена
				</Button>
			</Wrapper>
		</Modal>
	)
}

const mapStateToProps = (state) => ({
	isOpen: state.confirm.isOpen,
	confirmCallback: state.confirm.confirmCallback
})

export default connect(mapStateToProps, { closeConfirm })(Confirm);