import styled from 'styled-components';

const StyledSvg = styled.svg`
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

const Svg = ({data, colorArr}) => {

	const getStrokeDashoffset = (index) => {
		return  data.reduce((acc, curValue, i) => {
			if (i < index)
				return acc - curValue.percent
			return acc
		}, 0)
	}

	return (
		<StyledSvg viewBox='0 0 35 35'>
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
		</StyledSvg>
	)
}

export default Svg;