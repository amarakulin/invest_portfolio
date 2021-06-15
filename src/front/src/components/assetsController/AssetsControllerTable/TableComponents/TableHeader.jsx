import TableRow from './TableRow';
import styled from "styled-components";

const StyledTableHeader = styled.thead`
	display: contents;
`

const TableHeader = ({data}) => {
	return (
		<StyledTableHeader>
			<TableRow>
				{
					data.map((el, i) => {
						return <th key={i}>{el}</th>
					})
				}
			</TableRow>
		</StyledTableHeader>
	)
}

export default TableHeader;