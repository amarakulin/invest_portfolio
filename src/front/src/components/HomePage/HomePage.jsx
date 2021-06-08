import Header from '../Header/Header'
import {withAuthRedirectToSignUp} from '../../HOC/withRedirect'
import {connect} from 'react-redux';
import {compose} from 'redux'

const HomePage = (props) => {
	console.log(props)
	return (
		<Header />
	)
}


export default withAuthRedirectToSignUp(HomePage);
