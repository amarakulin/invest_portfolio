import Header from '../Header/Header';
import TopAssetsControllerContent from './TopAssetsControllerContent/TopAssetsControllerContent'
import AssetsControllerTable from './AssetsControllerTable/AssetsControllerTable'
import { Container } from '../Basic/Wrapper/Wrapper';
import { withAuthRedirectToSignUp } from '../../HOC/withRedirect'


const assetsController = (props) => {
	return (
		<>
			<Header />
			<Container>
				<TopAssetsControllerContent />
				<AssetsControllerTable />
			</Container>
		</>
	)
}

export default withAuthRedirectToSignUp(assetsController);