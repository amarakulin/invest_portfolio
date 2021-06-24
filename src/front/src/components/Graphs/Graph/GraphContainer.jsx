import { useEffect } from 'react';
import { connect } from 'react-redux';
import Graph from './Graph';
import GraphSlider from './GraphSlider/GraphSlider';
import GraphToggler from './GraphToggler/GraphToggler'
import Preloader from '../../Basic/Preloader/Preloader';
import { setTotalGraphData, getGraphData } from '../../../redux/graphReduser';
import { GraphPreloaderContainer } from './GraphUtils/GraphUtilsStyles'
import { toggleIsFetching } from '../../../redux/apiReduser'

const render = (props) => {
	if (props.isFetching || !props.totalData) {
		return <GraphPreloaderContainer> <Preloader color='black' /> </GraphPreloaderContainer>
	} else if (props.totalData?.lines.length === 0) {
		return 'Нет активов для отрисовки графика'
	} else {
		return (
			<>
				<Graph {...props} />
				<GraphSlider data={props.totalData} />
				<GraphToggler data={props.totalData} />
			</>
		)
	}
}

const GraphContainer = (props) => {

	useEffect(() => {
		props.getGraphData();
	}, [])

	return render(props)
}

const mapStateToProps = (state) => ({
	totalData: state.graph.data,
	isFetching: state.api.isFetching,
})

export default connect(mapStateToProps, { setTotalGraphData, toggleIsFetching, getGraphData })(GraphContainer);