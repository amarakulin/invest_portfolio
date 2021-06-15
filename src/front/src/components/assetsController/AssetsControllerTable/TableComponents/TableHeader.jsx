import TableRow from './TableRow';
import styled from "styled-components";

const StyledTableHeader = styled.thead`
	display: contents;
`

const TableHeader = () => {
	return (
		<StyledTableHeader>
			<TableRow>
				<th>Название</th>
				<th>Тикер</th>
				<th>Тип актива</th>
				<th>Биржа</th>
				<th>Цена</th>
				<th>Кол-во</th>
				<th>Сумма</th>
			</TableRow>
		</StyledTableHeader>
	)
}

export default TableHeader;