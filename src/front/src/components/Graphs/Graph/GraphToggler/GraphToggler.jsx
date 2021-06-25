import { connect } from 'react-redux';
import { setHiddenGraphName, removeHiddenGraphname } from '../../../../redux/graphReduser';
import { ToggeUl, ToggleLi } from './GraphTogglerStyles'

const GraphToggler = (props) => {
	
	const checkIsActive = (name) => {
		return props.hiddenName.includes(name)
	}

	const onClickHandler = (e) => {
		const name = e.target.dataset.name;

		if (!checkIsActive(name))
			props.setHiddenGraphName(name);
		else
			props.removeHiddenGraphname(name);
	}

	return (
		<ToggeUl>
			{
				props.data.lines.map((line, i) => {
					if (props.data.types[line[0]] !== 'line')
						return false
					let name = props.data.names[line[0]];
					return <ToggleLi 
								onClick={onClickHandler} 
								data-name={name} 
								color={props.data.color[line[0]] || '#000'}
								className={checkIsActive(name) && 'active'}
								key={i} 
							> 
								{name}
							</ToggleLi>
				})
			}
		</ToggeUl>
	)
}

const mapStateToProps = (state) => ({
	hiddenName: state.graph.hiddenGraphsName
})

export default connect(mapStateToProps, {setHiddenGraphName, removeHiddenGraphname})(GraphToggler);