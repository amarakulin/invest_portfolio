import HeaderTabs from './HeaderTabs'
import {AlignEndWrapper} from '../../Basic/Wrapper/Wrapper'
import {AddAsset} from '../../Basic/Button/Button'

const HeaderFooter = (props) => {
	return (
		<AlignEndWrapper>
			<HeaderTabs />
			<AddAsset onClick={props.openModal}> Добавить актив </AddAsset>
		</AlignEndWrapper>
	)
}

export default HeaderFooter;