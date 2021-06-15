import TableRow from './TableRow';
import styled from "styled-components";

const StyledTableBody = styled.tbody`
	display: contents;
`

const TableBody = () => {
	return (
		<StyledTableBody>
			<TableRow>
				<th>Газпром</th>
				<th>GAZP</th>
				<th>акция</th>
				<th>MOEX</th>
				<th>180руб.</th>
				<th>3 шт.</th>
				<th>540 руб.</th>
			</TableRow>
		</StyledTableBody>
	)
}

export default TableBody;