import Link from '../Basic/Link/Link'
import styled from 'styled-components';

const NotFoundContainer = styled.div`
	height: 100vh;
	width: 100wv;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
`

const NotFoundTitle = styled.h1`
	font-size: 15vw;
	line-height: 1;
	margin: 0;
	color: #74B791;
`

const Hint = styled.span`
	font-size: 14px;
	line-height: 17px;
	`

const NotFound = () => {
	return (
		<NotFoundContainer>
			<NotFoundTitle>404</NotFoundTitle>
			<Hint>Страница не найдена <br></br>
				<Link to='/login'>Войти</Link> или <Link to='/signup'>Зарегистрироваться</Link>
			</Hint>
		</NotFoundContainer>
	)
}

export default NotFound;