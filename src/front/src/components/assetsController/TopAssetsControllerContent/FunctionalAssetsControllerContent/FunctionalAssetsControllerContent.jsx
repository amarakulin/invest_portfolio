import CreateCategory from './FunctionalButtons/CreateCategory'
import Filter from './FunctionalButtons/Filter'
import {Wrapper} from '../../../Basic/Wrapper/Wrapper';

const FunctionalAssetsControllerContent = () => {
	return (
		<Wrapper>
			<CreateCategory />
			<Filter />
		</Wrapper>
	)
}

export default FunctionalAssetsControllerContent;