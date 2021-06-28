import HeaderTabs from './HeaderTabs'
import {AlignEndWrapper} from '../../Basic/Wrapper/Wrapper'
import {AddAsset, HeaderButton} from '../../Basic/Button/Button'
import { ButtonWrapper } from './HeaderStyles'
import SetCategoryContainer from '../../Category/SetCategory/SetCategoryContainer';

const HeaderFooter = (props) => {
	return (
		<AlignEndWrapper>
			<HeaderTabs />
			<ButtonWrapper>
				<SetCategoryContainer />
				<AddAsset onClick={() => props.openModal(true)}> Добавить актив </AddAsset>
				<HeaderButton onClick={props.logout}> Выйти </HeaderButton>
			</ButtonWrapper>
		</AlignEndWrapper>
	)
}

export default HeaderFooter;