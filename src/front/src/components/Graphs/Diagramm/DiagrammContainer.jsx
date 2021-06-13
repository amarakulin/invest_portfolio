import { useEffect } from 'react';
import Diagramm from './Diagramm';
import { connect } from 'react-redux';
import { getDiagrammData } from '../../../redux/diagrammReduser';
import { GraphPreloaderContainer } from '../Graph/GraphUtils/GraphStyledUtils'
import Preloader from '../../Basic/Preloader/Preloader';

const DiagrammContainer = (props) => {
	useEffect(() => {
		props.getDiagrammData();
	}, [])

	return (
		props.isFetching || !props.data
		? <GraphPreloaderContainer> <Preloader color='black' /> </GraphPreloaderContainer>
		: <Diagramm data={props.data}/>
	)
}

const mapDispatchToProps = (state) => ({
	data: state.diagramm.data,
	isFetching: state.api.isFetching
}) 

export default connect(mapDispatchToProps, {getDiagrammData})(DiagrammContainer);