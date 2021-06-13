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
	&.active,
	&:hover {
		opacity: 0.9;
		stroke-width: 3;
	}
	&.unactive {
		stroke: #E3E3E3;
	}
`

const SvgText = styled.text`
	font-size: 0.1em;
	color: #8692A6;
`

const SvgTSpan = styled.tspan`
	text-anchor: middle;
`

const Svg = (props) => {

	const getStrokeDashoffset = (index) => {
		return  props.data.reduce((acc, curValue, i) => {
			if (i < index)
				return acc - curValue.percent
			return acc
		}, 0)
	}

	const getClassName = (i) => {
		if (props.activeIndex !== null) {
			if (props.activeIndex === i)
				return 'active';
			else
				return 'unactive';
		}
		return '';
	}

	return (
		<StyledSvg viewBox='0 0 35 35'>
			{
				props.data.map((el, i) => {
					return <Circle
						r='15.9'
						cx='50%'
						cy='50%'
						stroke={props.colorArr[i]}
						strokeDasharray={`${el.percent} 100`}
						strokeDashoffset={getStrokeDashoffset(i)}
						key={el.name}
						className={getClassName(i)}
						onMouseEnter={() => props.mouseEnterHandler(i)}
						onMouseLeave={() => props.mouseLeaveHandler()}
					/>
				})
			}
			{
				props.activeIndex !== null && 
					<SvgText text-anchor="end" x="50%" y="50%">
							<SvgTSpan x='50%' y ='46%'>
								{props.data[props.activeIndex].percent + '%'}
							</SvgTSpan>
							<SvgTSpan x='50%' y ='53%'>
								{props.data[props.activeIndex].value}
							</SvgTSpan>
					</SvgText>
			}
		</StyledSvg>
	)
}

export default Svg;