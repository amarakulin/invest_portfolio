import { LegendContainer, LegenTitle, LegendList, LegendListItem} from './LegendStyles';

const Legend = (props) => {

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
		<LegendContainer>
			<LegenTitle>Категории: </LegenTitle>
			<LegendList>
				{
					props.data.map((el, i) => {
						return <LegendListItem 
								color={props.data[i].color}
								key={el.name}
								onMouseEnter={() => props.mouseEnterHandler(i)}
								onMouseLeave={() => props.mouseLeaveHandler()}
								className={getClassName(i)}
							>
								{`${el.name} (${el.ticker})`}
							</LegendListItem>
					})
				}
			</LegendList>
		</LegendContainer>
	)
}

export default Legend;