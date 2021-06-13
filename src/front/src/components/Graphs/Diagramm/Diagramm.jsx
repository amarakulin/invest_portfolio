import styled from 'styled-components';
import Legend from './Legend/Legend'

const DiargammCanvas = styled.div`
	display: flex;
	justify-content: space-around;
	align-items: flex-start;
`

const Svg = styled.svg`
	max-height: 400px;
	max-width: 400px;
	min-height: 200px;
	min-width: 200px;
	width: 40%;
	height: 100%;
`

const Circle = styled.circle`
	fill: none;
	stroke-width: 2;
	cursor: pointer;
	transition: all 0.2s ease;
	&:hover {
		opacity: 0.9;
		stroke-width: 3;
	}
`

const randomColor = () => '#' + Math.floor(Math.random() * 16777215).toString(16);

const Diagramm = ({data}) => {
	const colorArr = Array.from(new Array(data.length), el => el = randomColor());
	
	const getStrokeDashoffset = (index) => {
		return  data.reduce((acc, curValue, i) => {
			if (i < index)
				return acc - curValue.percent
			return acc
		}, 0)
	}

	return (
		<DiargammCanvas>
			<Svg viewBox='0 0 35 35'>
				{
					data.map((el, i) => {
						return <Circle 
							r='15.9'
							cx='50%'
							cy='50%'
							stroke={colorArr[i]}
							strokeDasharray={`${el.percent} 100`}
							strokeDashoffset={getStrokeDashoffset(i)}
							key={i}
						/>
					})
				}
			</Svg>
			<Legend data={data} colorArr={colorArr} />
		</DiargammCanvas>
	)
}

export default Diagramm;