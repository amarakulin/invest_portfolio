import FunctionalAssetsControllerContent from './FunctionalAssetsControllerContent/FunctionalAssetsControllerContent'
import {SmallTitle} from '../../Basic/Title/Title';
import {Wrapper} from '../../Basic/Wrapper/Wrapper';

const TopAssetsControllerContent = () => {
	return (
		<Wrapper>
			<SmallTitle>Мои активы</SmallTitle>
			<FunctionalAssetsControllerContent />
		</Wrapper>
	)
}

export default TopAssetsControllerContent;