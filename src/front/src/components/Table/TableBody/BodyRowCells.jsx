import TableOptions from '../TableOptons/TableOptions';
import EditAssetAmountForm from '../../Forms/EditAssetAmountForm/EditAssetAmountForm';
import { TabelBodyCell } from './tableBodyStyles';
import { connect } from 'react-redux';
import { setSelectedAsset, resetSelectedAsset } from '../../../redux/assetsTableReduser';
import { showAlert } from '../../../redux/alertReduser';

const BodyRowCells = ({ data, order, dataIndex, ...props }) => {

	const getBodyCurrectTableBodyCell = (el) => {
		if (props.selectedAsset.ticker === data[dataIndex].ticker && el === 'amount') {
			return <EditAssetAmountForm
				value={data[dataIndex][el]}
				resetSelectedAsset={props.resetSelectedAsset}
				ticker={data[dataIndex].ticker}
				type={props.selectedAsset.type}
				showAlert={props.showAlert}
			/>
		} else {
			return data[dataIndex][el]
		}
	}

	return (
		<>
			{
				order.map((el, i) => {
					return <TabelBodyCell key={i}>
						{getBodyCurrectTableBodyCell(el)}
					</TabelBodyCell>
				})
			}
			<TableOptions
				setSelectedAsset={props.setSelectedAsset}
				ticker={data[dataIndex].ticker}
				showAlert={props.showAlert}
			/>
		</>
	);
}

const mapStateToProps = (state) => ({
	selectedAsset: state.table.selectedAsset
})

export default connect(mapStateToProps, { setSelectedAsset, resetSelectedAsset, showAlert })(BodyRowCells);