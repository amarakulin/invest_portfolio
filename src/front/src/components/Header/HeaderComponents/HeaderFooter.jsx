import HeaderTabs from './HeaderTabs'
import {AlignEndWrapper} from '../../Basic/Wrapper/Wrapper'
import {AddAsset, HeaderButton} from '../../Basic/Button/Button'
import styled from 'styled-components';

const ButtnoWrapper = styled.div`
	display: flex;
	align-items: center;
	justif-content: space-between;
`


const HeaderFooter = (props) => {
	return (
		<AlignEndWrapper>
			<HeaderTabs />
			<ButtnoWrapper>
				<AddAsset onClick={props.openModal}> Добавить актив </AddAsset>
				<HeaderButton onClick={props.logout}> Выйти </HeaderButton>
			</ButtnoWrapper>
		</AlignEndWrapper>
	)
}

export default HeaderFooter;