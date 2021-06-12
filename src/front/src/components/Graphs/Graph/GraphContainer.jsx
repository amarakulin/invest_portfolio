import {useEffect} from 'react';
import { connect } from 'react-redux';
import Graph from './Graph';
import GraphSlider from './GraphSlider/GraphSlider';
import GraphToggler from './GraphToggler/GraphToggler'
import Preloader from '../../Basic/Preloader/Preloader';
import { setTotalGraphData, getGraphData } from '../../../redux/graphReduser';
import { GraphPreloaderContainer } from './GraphUtils/GraphStyledUtils'
import { toggleIsFetching } from '../../../redux/apiReduser'

const GraphContainer = (props) => {

	useEffect(() => {
		props.getGraphData();
	}, [])

	return (
		props.isFetching || !props.totalData
			? <GraphPreloaderContainer> <Preloader color='black'/> </GraphPreloaderContainer>
			: <>
				<Graph {...props}/>
				<GraphSlider data={props.totalData}/>
				<GraphToggler data={props.totalData}/>
			</>
	)
}

const mapStateToProps = (state) => ({
	totalData: state.graph.data,
	isFetching: state.api.isFetching,
})

export default connect(mapStateToProps, {setTotalGraphData, toggleIsFetching, getGraphData})(GraphContainer);