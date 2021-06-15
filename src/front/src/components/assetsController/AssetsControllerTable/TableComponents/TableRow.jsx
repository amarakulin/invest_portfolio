import styled from "styled-components";

const StyledTableRow = styled.tr`
	display: contents;
`

const TableRow = (props) => {
	return (
		<StyledTableRow>
			{props.children}
		</StyledTableRow>
	)
}

export default TableRow;