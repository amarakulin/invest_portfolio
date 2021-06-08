import styled from 'styled-components';
import logo from '../../../assets/logo.svg'
import {LogoImg} from '../../Basic/Img/Img'
import {StartWrapper} from '../../Basic/Wrapper/Wrapper'

const Name = styled.h2`
	font-style: normal;
	font-weight: bold;
	font-size: 30px;
	line-height: 36px;
	color: #343A40;
	margin: 0 0 0 36px;
`

const Greeting = (props) => {
	return (
		<StartWrapper marginBottom={71}>
			<LogoImg src={logo} alt="logo" />
			<Name> { `Добрый день, ${props.name}` } </Name>
		</StartWrapper>
	)
}

export default Greeting;