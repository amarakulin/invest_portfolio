import { useState } from 'react';
import Modal from '../Modal/Modal';
import { SmallTitle } from '../Basic/Title/Title';
import { Wrapper } from '../Basic/Wrapper/Wrapper';
import Button from '../Basic/Button/Button';
import { connect } from 'react-redux';
import { confirmInterface } from '../../redux/confirmReduser'

const Confirm = (props) => {
	return (
		<Modal closeModal={props.close} display={props.isOpen}>
			<SmallTitle marginBottom='40'>Подтвердите действие</SmallTitle>
			<Wrapper>
				<Button width='40'>Подтвердить</Button>
				<Button width='40'>Отмена</Button>
			</Wrapper>
		</Modal>
	)
}

const mapStateToProps = (state) => ({
	isOpen: state.confirm.isOpen,
	hasConfirmed: state.confirm.hasConfirmed
})

export default connect(mapStateToProps, {...confirmInterface})(Confirm);