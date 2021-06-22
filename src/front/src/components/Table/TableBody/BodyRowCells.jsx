import TableOptions from '../TableOptons/TableOptions';
import EditAssetAmountForm from '../../Forms/EditAssetAmountForm/EditAssetAmountForm'
import styled from 'styled-components';

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

const BodyRowCells = ({ data, order, editModeByTicker, setEditMode, dataIndex }) => {
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

export default BodyRowCells;