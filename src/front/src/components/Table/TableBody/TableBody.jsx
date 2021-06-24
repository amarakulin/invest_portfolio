import TableRow from '../TableRow';
import BodyRowCells from './BodyRowCells';

const TableBody = ({ data, order }) => {
	return (
		data.map((_, dataIndex) => {
			return (
				<TableRow
					bodyRow={true}
					cols={order.length}
					key={data[dataIndex].ticker}
					bordercolor='#F3F3FB'
				>
					{
						<BodyRowCells
							data={data}
							order={order}
							dataIndex={dataIndex}
						/>
					}
				</TableRow>
			)
		})
	)
}

export default TableBody;