import CreateCategoryContainer from './../../../Category/CreateCategory/CreateCategoryContainer'
import Filter from './FunctionalButtons/Filter'
import {Wrapper} from '../../../Basic/Wrapper/Wrapper';

const FunctionalAssetsControllerContent = () => {
	return (
		<Wrapper>
			<CreateCategoryContainer />
			<Filter />
		</Wrapper>
	)
}

export default FunctionalAssetsControllerContent;