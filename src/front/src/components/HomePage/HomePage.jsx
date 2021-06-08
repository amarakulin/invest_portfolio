import Header from '../Header/Header'
import {withAuthRedirectToSignUp} from '../../HOC/withRedirect'
import {connect} from 'react-redux';
import {compose} from 'redux'
import {logout} from '../../redux/authReduser'
import {openModal, closeModal} from '../../redux/modalReduser'

const HomePage = (props) => {
	return (
		<Header 
			logout={props.logout}
			name={props.name}
			isModalOpen={props.isModalOpen}
			closeModal={props.closeModal}
			openModal={props.openModal}
		/>
	)
}


const mapStateToProps = (state) => ({
	isActive: state.auth.isAuth,
	name: state.auth.name,
	isModalOpen: state.modal.isOpen
})

export default compose(
	connect(mapStateToProps, {logout, closeModal, openModal}),
	withAuthRedirectToSignUp
)(HomePage)
