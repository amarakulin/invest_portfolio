import styled from 'styled-components';
const LegendContainer = styled.div`
	width: 40%;
`

const LegenTitle = styled.h3`
	font-style: normal;
	font-weight: bold;
	font-size: 30px;
	line-height: 36px;
	color: #343A40;
	margin: 0 0  43px 0;
`

const LegendList = styled.ul`
	padding: 0;
	list-style-type: none;
`

const LegendListItem = styled.li`
	font-style: normal;
	font-weight: normal;
	font-size: 18px;
	line-height: 22px;
	color: #8692A6;
	margin-bottom: 25px;
	display: flex;
	align-items: center;
	cursor: pointer;
	&::before {
		content: '';
		width: 25px;
		height: 25px;
		margin-right: 10px;
		display: block;
		background-color: ${props => props.color};
	}
`

const Legend = ({data, colorArr}) => {
	return (
		<LegendContainer>
			<LegenTitle>Категории: </LegenTitle>
			<LegendList>
				{
					data.map((el, i) => {
						return <LegendListItem color={colorArr[i]}>{`${el.name} (${el.ticker})`}</LegendListItem>
					})
				}
			</LegendList>
		</LegendContainer>
	)
}

export default Legend;