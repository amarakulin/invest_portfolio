import { useState } from 'react';
import TableRow from './TableRow';
import styled from "styled-components";
import TableOptions from './TableOptons/TableOptions';
import EditAssetAmountForm from '../Forms/EditAssetAmountForm/EditAssetAmountForm'

const TabelBodyCell = styled.div`
	padding: 20px 12px 20px 12px;
	font-style: normal;
	font-weight: normal;
	font-size: 14px;
	color: #272833;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	display: flex;
	align-items: center;
	${props => {
		return props.options && 'justify-content: center; padding: 0 12px;'
	}}
`

const RowCells = ({ data, order, editModeByTicker, setEditMode, dataIndex }) => {
	const renderTableOptions = () => {
		return <TableOptions
			setEditMode={setEditMode}
			ticker={data[dataIndex].ticker}
		/>
	}

	const renderBodyCells = () => {
		return (
			order.map((el, i) => {
				return (
					<TabelBodyCell key={i}>
						{
							editModeByTicker === data[dataIndex].ticker && el === 'amount'
								? <EditAssetAmountForm
									value={data[dataIndex][el]}
									setEditMode={setEditMode}
									ticker={data[dataIndex].ticker}
								/>
								: data[dataIndex][el]
						}
					</TabelBodyCell>
				)
			})
		)
	}

	return (
		<>
			{renderBodyCells()}
			{renderTableOptions()}
		</>
	)
}

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
						<RowCells
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