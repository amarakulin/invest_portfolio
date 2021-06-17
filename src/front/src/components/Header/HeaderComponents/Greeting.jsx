import logo from '../../../assets/logo.svg';
import {LogoImg} from '../../Basic/Img/Img';
import {StartWrapper} from '../../Basic/Wrapper/Wrapper';
import { Name } from './HeaderStyles';

const Greeting = (props) => {
	return (
		<StartWrapper marginBottom={71}>
			<LogoImg src={logo} alt="logo" />
			<Name> { `Добрый день, ${props.name}` } </Name>
		</StartWrapper>
	)
}

export default Greeting;