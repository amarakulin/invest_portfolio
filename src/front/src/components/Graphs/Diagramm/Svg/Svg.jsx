import { StyledSvg, Circle, SvgText, SvgTSpan } from './SvgStyles';

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
						stroke={props.data[i].color}
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