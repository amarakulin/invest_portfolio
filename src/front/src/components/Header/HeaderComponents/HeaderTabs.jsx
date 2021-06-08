import styled from 'styled-components';
import { TabLink } from '../../Basic/Link/Link'
import { StartWrapper } from '../../Basic/Wrapper/Wrapper'

const checkIsActive = (match, location) => {
	if(!location) 
		return false;

    return location.pathname === "/";
}

const HeaderTabs = () => {
	return (
		<StartWrapper>
			<TabLink isActive={checkIsActive} activeClassName='active' to='/'> Мой портфель </TabLink>
			<TabLink activeClassName='active' to='/assets'> Мои активы </TabLink>
		</StartWrapper>
	);
}

export default HeaderTabs;