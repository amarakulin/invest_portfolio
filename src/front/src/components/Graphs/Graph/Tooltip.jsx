import styled from 'styled-components';


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

	const containerStyle = {
		padding: '20px',
		backgroundColor: '#fff',
		borderRadius: '6px',
		position: 'absolute',
		boxShadow: '0px 0px 10px 2px rgba(20, 20, 21, 0.2)',
		display: 'inline-block',
		top: `${props.top}px`,
		left: `${props.left}px`,
	}

	const titleStyle = {
		margin: 0,
		fontSize: '16px',
		lineHeight: '16px',
		fontWeight: 'normal',
	}

	const listStyle = {
		listStyleType: 'circle'
	}

	return (
		<div style={containerStyle}>
			<h2 style={titleStyle}> {props.title} </h2>
			<ul style={listStyle}>
				{
					props.data.map((item, i) => {
						return <TooltipListItem key={i} >
							<TooltipListItemValue> { item.value } </TooltipListItemValue>
							<TooltipListItemName> { item.name } </TooltipListItemName>
						</TooltipListItem>
					})
				}
			</ul>
		</div>
	)
}

export default Tooltip;