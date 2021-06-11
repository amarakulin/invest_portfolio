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
		fontSize: '18px',
		lineHeight: '18px',
		fontWeight: 'normal',
		textAlign: 'center'
	}

	const listStyle = {
		listStyleType: 'none',
		padding: '0px',
		display: 'flex',
		flexDirection: 'column',
		alignItems: 'start',
		justifyContent: 'space-between',
		margin: '0'
	}

	const listItemStyle = {
		display: 'flex',
		alignItems: 'center',
	}

	return (
		<div style={containerStyle}>
			<h2 style={titleStyle}> {props.title} </h2>
			<ul style={listStyle}>
				{
					props.data.map((item, i) => {
						return <ul style={listStyle} key={i} >
							<li style={listItemStyle, {color: item.color}}> {`${item.name}: ${item.value}`} </li>
						</ul>
					})
				}
			</ul>
		</div>
	)
}

export default Tooltip;