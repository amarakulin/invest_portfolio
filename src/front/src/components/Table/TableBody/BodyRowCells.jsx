import TableOptions from '../TableOptons/TableOptions';
import EditAssetAmountForm from '../../Forms/EditAssetAmountForm/EditAssetAmountForm';
import { TabelBodyCell } from './tableBodyStyles'

const BodyRowCells = ({ data, order, editModeByTicker, setEditMode, dataIndex }) => {
	const getTableBodyRowCells = () => {
		const TableBodyRow = () => {
			return <TableOptions
				setEditMode={setEditMode}
				ticker={data[dataIndex].ticker}
			/>
		}

		const getBodyCurrectTableBodyCell = (el ,i) => {
			if (editModeByTicker.ticker === data[dataIndex].ticker && el === 'amount') {
				return <EditAssetAmountForm
					value={data[dataIndex][el]}
					setEditMode={setEditMode}
					ticker={data[dataIndex].ticker}
					type={editModeByTicker.type}
				/>
			} else {
				return data[dataIndex][el]
			}
		}

		const renderTableBodyCell = (el, i) => (
			<TabelBodyCell key={i}>
				{getBodyCurrectTableBodyCell(el, i)}
			</TabelBodyCell>
		)

		return (
			<>
				{order.map(renderTableBodyCell)}
				{TableBodyRow()}
			</>
		);
	}

	return getTableBodyRowCells()
}

export default BodyRowCells;