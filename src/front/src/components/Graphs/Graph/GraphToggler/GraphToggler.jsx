import { connect } from 'react-redux';
import { setHiddenGraphName, removeHiddenGraphname } from '../../../../redux/graphReduser';
import styled from 'styled-components';

const ToggeUl = styled.ul`
	list-style-type: none;
	padding: 0;
	display: flex;
	align-items: center;
	justify-content: center;
`

const ToggleLi = styled.li`
	margin-right: 15px;
	display: flex;
	align-items: center;
	cursor: pointer;
	font-style: normal;
	font-weight: 500;
	font-size: 14px;
	line-height: 17px;
	color: #8692A6;
	transition: all 0.2s ease;
	&::before {
		content: '';
		display: block;
		width: 15px;
		height: 15px;
		border-radius: 50%;
		background-color: ${props => props.color};
		margin-right: 5px;
	}
	&:hover {
		color: #343A40;
	}
	&.active {
		&::before {
			background-color: #F0F0F0;
		}
	}
`

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
								color={props.data.color[line[0]]}
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