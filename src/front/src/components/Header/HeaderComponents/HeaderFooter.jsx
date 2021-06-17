import HeaderTabs from './HeaderTabs'
import {AlignEndWrapper} from '../../Basic/Wrapper/Wrapper'
import {AddAsset, HeaderButton} from '../../Basic/Button/Button'
import { ButtonWrapper } from './HeaderStyles'

const HeaderFooter = (props) => {
	return (
		<AlignEndWrapper>
			<HeaderTabs />
			<ButtonWrapper>
				<AddAsset onClick={props.openModal}> Добавить актив </AddAsset>
				<HeaderButton onClick={props.logout}> Выйти </HeaderButton>
			</ButtonWrapper>
		</AlignEndWrapper>
	)
}

export default HeaderFooter;