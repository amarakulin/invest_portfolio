import styled from 'styled-components';
import Legend from './Legend/Legend'
import Svg from './Svg/Svg'

const DiargammCanvas = styled.div`
	display: flex;
	justify-content: space-around;
	align-items: center;
	margin-top: 85px;
`

const randomColor = () => '#' + Math.floor(Math.random() * 16777215).toString(16);

const colorArr = Array.from(new Array(100), el => el = randomColor());

const Diagramm = (props) => {
	return (
		<DiargammCanvas>
			<Svg
				{...props}
				colorArr={colorArr}
			/>
			<Legend
				{...props}
				colorArr={colorArr}
			/>
		</DiargammCanvas>
	)
}

export default Diagramm;