import TableRow from './TableRow';
import styled from "styled-components";

const StyledTableBody = styled.tbody`
	display: contents;
`

const TableBody = ({data}) => {
	return (
		<StyledTableBody>
			{
				data.map((el, i) => {
					return (
						<TableRow key={i}>
							{
								el.map((el, i) => {
									return <td key={i}>{el}</td>
								})
							}
						</TableRow>
					)
				})
			}
		</StyledTableBody>
	)
}

export default TableBody;