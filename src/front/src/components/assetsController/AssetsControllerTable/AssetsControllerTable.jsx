import { useEffect } from 'react';
import { connect } from 'react-redux';
import { getTableData } from '../../../redux/assetsTableReduser';
import Preloader from '../../Basic/Preloader/Preloader'
import Table from '../../Table/Table'

const AssetsControllerTable = (props) => {

	useEffect(() => {
		props.getTableData();
	}, []);

	return (
		props.isFetching || !Object.keys(props.data).length 
			? <Preloader color='black' /> 
			: <Table data={props.data} />
	)
}

const mapDispatchToProps = (state) => ({
	data: state.table.data,
	isFetching: state.table.isFetching,
})

export default connect(mapDispatchToProps, {getTableData})(AssetsControllerTable);