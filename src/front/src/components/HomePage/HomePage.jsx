import Header from '../Header/Header'
import GraphContainer from '../Graphs/Graph/GraphContainer'
import DiagrammContainer from '../Graphs/Diagramm/DiagrammContainer'
import { Container } from '../Basic/Wrapper/Wrapper';
import {withAuthRedirectToSignUp} from '../../HOC/withRedirect'
import styled from 'styled-components';

const HomePageContainer = styled.div`
	padding-bottom: 30vh;
`
const HomePage = (props) => {
	
	return (
		<HomePageContainer>
			<Header />
			<Container>
				<GraphContainer />
				<DiagrammContainer />
			</Container>
		</HomePageContainer>
	)
}

export default withAuthRedirectToSignUp(HomePage)
