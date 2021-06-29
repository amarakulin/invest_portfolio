import Header from '../Header/Header';
import AssetsPageHead from './AssetsPageHead/AssetsPageHead'
import AssetsPageTable from './AssetsPageTable/AssetsPageTable'
import { Container } from '../Basic/Wrapper/Wrapper';
import { withAuthRedirectToSignUp } from '../../HOC/withRedirect'


const AssetsPage = (props) => {
	return (
		<>
			<Header />
			<Container>
				<AssetsPageHead />
				<AssetsPageTable />
			</Container>
		</>
	)
}

export default withAuthRedirectToSignUp(AssetsPage);