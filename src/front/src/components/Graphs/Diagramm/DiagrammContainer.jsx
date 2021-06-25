import { useEffect } from 'react';
import Diagramm from './Diagramm';
import { connect } from 'react-redux';
import { getDiagrammData, setActiveCircle } from '../../../redux/diagrammReduser';
import { GraphPreloaderContainer } from '../Graph/GraphUtils/GraphUtilsStyles'
import Preloader from '../../Basic/Preloader/Preloader';

const render = (props) => {
	
	const mouseEnterHandler = (i) => {
		props.setActiveCircle(i);
	}
	
	const mouseLeaveHandler = () => {
		props.setActiveCircle(null);
	}

	if (props.isFetching || !props.data) {
		return <GraphPreloaderContainer> <Preloader color='black' /> </GraphPreloaderContainer>
	} else if (props.data.length === 0) {
		return <div>Нет активов для отрисовки диаграммы</div>
	} else {
		return (
			<Diagramm 
				data={props.data}
				setActiveCircle={props.setActiveCircle}
				activeIndex={props.activeIndex}
				mouseEnterHandler={mouseEnterHandler}
				mouseLeaveHandler={mouseLeaveHandler}
			/>
		)
	}
}

const DiagrammContainer = (props) => {
	useEffect(() => {
		props.getDiagrammData();
	}, [])

	return render(props)
}

const mapDispatchToProps = (state) => ({
	data: state.diagramm.data,
	activeIndex: state.diagramm.activeIndex,
	isFetching: state.api.isFetching
}) 

export default connect(mapDispatchToProps, {getDiagrammData, setActiveCircle})(DiagrammContainer);