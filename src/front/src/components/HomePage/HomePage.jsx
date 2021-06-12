import Header from '../Header/Header'
import GraphContainer from '../Graphs/Graph/GraphContainer'
import { Container } from '../Basic/Wrapper/Wrapper';
import {withAuthRedirectToSignUp} from '../../HOC/withRedirect'

const HomePage = (props) => {
	
	return (
		<>
			<Header />
			<Container>
				<GraphContainer />
			</Container>
		</>
	)
}

export default withAuthRedirectToSignUp(HomePage)
