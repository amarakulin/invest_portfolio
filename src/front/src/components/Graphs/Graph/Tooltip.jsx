import styled from 'styled-components';

const TooltipTitle = styled.h2`
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
		<>
			<TooltipTitle> { props.title } </TooltipTitle>
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
		</>
	)
}

export default Tooltip;