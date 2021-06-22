import TableBody from './TableBody/TableBody'
import TableHeader from './TableHeader'
import styled from "styled-components";
import Confirm from '../Confirm/Сonfirm'

const StyledTable = styled.div`
	min-width: 100%;
`

const Table = ({ data }) => {
	return (
		<StyledTable>
			<Confirm />
			<TableHeader data={data.header} order={data.order} />
			<TableBody data={data.body} order={data.order} />
		</StyledTable>
	);
}

export default Table;