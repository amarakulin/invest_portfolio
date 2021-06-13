import styled from 'styled-components';
import Legend from './Legend/Legend'
import Svg from './Svg/Svg'

const DiargammCanvas = styled.div`
	display: flex;
	justify-content: space-around;
	align-items: flex-start;
`

const randomColor = () => '#' + Math.floor(Math.random() * 16777215).toString(16);

const Diagramm = ({data}) => {
	const colorArr = Array.from(new Array(data.length), el => el = randomColor());
	
	return (
		<DiargammCanvas>
			<Svg data={data} colorArr={colorArr}/>
			<Legend data={data} colorArr={colorArr}/>
		</DiargammCanvas>
	)
}

export default Diagramm;