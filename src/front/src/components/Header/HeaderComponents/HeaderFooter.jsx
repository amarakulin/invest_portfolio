import HeaderTabs from './HeaderTabs'
import {AlignEndWrapper} from '../../Basic/Wrapper/Wrapper'
import {AddAsset} from '../../Basic/Button/Button'

const HeaderFooter = () => {
	return (
		<AlignEndWrapper>
			<HeaderTabs />
			<AddAsset>Добавить актив</AddAsset>
		</AlignEndWrapper>
	)
}

export default HeaderFooter;