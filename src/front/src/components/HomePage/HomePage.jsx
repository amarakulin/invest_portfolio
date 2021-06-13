import Header from '../Header/Header'
import GraphContainer from '../Graphs/Graph/GraphContainer'
import DiagrammContainer from '../Graphs/Diagramm/DiagrammContainer'
import { Container } from '../Basic/Wrapper/Wrapper';
import {withAuthRedirectToSignUp} from '../../HOC/withRedirect'

const HomePage = (props) => {
	
	return (
		<>
			<Header />
			<Container>
				<GraphContainer />
				<DiagrammContainer />
			</Container>
		</>
	)
}

export default withAuthRedirectToSignUp(HomePage)
