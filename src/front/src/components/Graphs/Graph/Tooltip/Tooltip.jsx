import { containerStyle, titleStyle, listStyle, listItemStyle } from './TooltipStyles'

const Tooltip = (props) => {
	return (
		<div style={Object.assign({}, containerStyle, {top: props.top, left: props.left})}>
			<h2 style={titleStyle}> {props.title} </h2>
			<ul style={listStyle}>
				{
					props.data.map((item, i) => {
						if (item.value === null || item.value === undefined)
							return null
						return <ul style={listStyle} key={i} >
							<li style={Object.assign({}, listItemStyle, {color: item.color})}> {`${item.name}: ${item.value}`} </li>
						</ul>
					})
				}
			</ul>
		</div>
	)
}

export default Tooltip;