import styled from 'styled-components';


const TooltipContainer = styled.div`
	padding: 20px;
	background-color: #fff;
	box-shadow: 0px 0px 10px 2px rgba(20, 20, 21, 0.2);
	border-radius: 6px;
	display: inline-block;
	position: absolute;
	top: ${props => props.top}px;
	left: ${props => props.left}px;
`

const TooltipTitle = styled.h2`
	margin: 0;
	font-size: 16px;
	line-height: 16px;
	font-weight: normal;
`

const TooltipList = styled.ul`
	list-style-type: circle;
`

const TooltipListItem = styled.li`
`

const TooltipListItemValue = styled.div`
`

const TooltipListItemName = styled.div`
`

const Tooltip = (props) => {
	return (
		<TooltipContainer left={props.left} top={props.top}>
			<TooltipTitle> {props.title} </TooltipTitle>
			<TooltipList >
				{
					props.data.map((item, i) => {
						return <TooltipListItem key={i} >
							<TooltipListItemValue> { item.value } </TooltipListItemValue>
							<TooltipListItemName> { item.name } </TooltipListItemName>
						</TooltipListItem>
					})
				}
			</TooltipList>
		</TooltipContainer>
	)
}

export default Tooltip;