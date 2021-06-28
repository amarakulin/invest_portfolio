import Header from '../Header/Header'
import GraphContainer from '../Graphs/Graph/GraphContainer'
import TotalPrice from '../TotalPrice/TotalPrice'
import DiagrammContainer from '../Graphs/Diagramm/DiagrammContainer'
import { Container } from '../Basic/Wrapper/Wrapper';
import { withAuthRedirectToSignUp } from '../../HOC/withRedirect'
import styled from 'styled-components';
import { connect } from 'react-redux';
import { compose } from 'redux';

const HomePageContainer = styled.div`
	padding-bottom: 30vh;
`
const HomePage = (props) => {
	return (
		<HomePageContainer>
			<Header />
			<Container>
				<TotalPrice totalPrice={props.totalPrice}/>
				<GraphContainer />
				<DiagrammContainer />
			</Container>
		</HomePageContainer>
	)
}

const mapStateToProps = (state) => ({
	totalPrice: state.assets.totalPrice
})

export default compose(
	connect(mapStateToProps, {}),
	withAuthRedirectToSignUp
)(HomePage)
