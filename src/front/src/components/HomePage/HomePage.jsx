import Header from '../Header/Header'
import {withAuthRedirectToSignUp} from '../../HOC/withRedirect'

const HomePage = (props) => {
	return (
		<Header />
	)
}

export default withAuthRedirectToSignUp(HomePage)
