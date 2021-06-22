import { useState } from 'react';
import TableRow from '../TableRow';
import BodyRowCells from './BodyRowCells';

const TableBody = ({ data, order }) => {
	const [editModeByTicker, setEditMode] = useState(null);

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
							editModeByTicker={editModeByTicker}
							setEditMode={setEditMode}
						/>
					}
				</TableRow>
			)
		})
	)
}

export default TableBody;