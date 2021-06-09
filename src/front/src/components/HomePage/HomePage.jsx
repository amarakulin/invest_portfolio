import Header from '../Header/Header'
import Graph from '../Graphs/Graph/Graph'
import { Container } from '../Basic/Wrapper/Wrapper';
import {withAuthRedirectToSignUp} from '../../HOC/withRedirect'

const HomePage = (props) => {
	return (
		<>
			<Header />
			<Container>
				<Graph />
			</Container>
		</>
	)
}

export default withAuthRedirectToSignUp(HomePage)
