import FunctionalAssetsPage from './FunctionalAssetsPage/FunctionalAssetsControllerContent'
import {SmallTitle} from '../../Basic/Title/Title';
import {Wrapper} from '../../Basic/Wrapper/Wrapper';

const AssetsPageHead = () => {
	return (
		<Wrapper marginBottom={72}>
			<SmallTitle>Мои активы</SmallTitle>
			<FunctionalAssetsPage />
		</Wrapper>
	)
}

export default AssetsPageHead;