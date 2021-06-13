import { useEffect } from 'react';
import Diagramm from './Diagramm';
import { connect } from 'react-redux';
import { getDiagrammData, setActiveCircle } from '../../../redux/diagrammReduser';
import { GraphPreloaderContainer } from '../Graph/GraphUtils/GraphStyledUtils'
import Preloader from '../../Basic/Preloader/Preloader';

const DiagrammContainer = (props) => {
	useEffect(() => {
		props.getDiagrammData();
	}, [])

	const mouseEnterHandler = (i) => {
		props.setActiveCircle(i);
	}

	const mouseLeaveHandler = () => {
		props.setActiveCircle(null);
	}

	return (
		props.isFetching || !props.data
		? <GraphPreloaderContainer> <Preloader color='black' /> </GraphPreloaderContainer>
		: <Diagramm 
				data={props.data}
				setActiveCircle={props.setActiveCircle}
				activeIndex={props.activeIndex}
				mouseEnterHandler={mouseEnterHandler}
				mouseLeaveHandler={mouseLeaveHandler}
			/>
	)
}

const mapDispatchToProps = (state) => ({
	data: state.diagramm.data,
	activeIndex: state.diagramm.activeIndex,
	isFetching: state.api.isFetching
}) 

export default connect(mapDispatchToProps, {getDiagrammData, setActiveCircle})(DiagrammContainer);