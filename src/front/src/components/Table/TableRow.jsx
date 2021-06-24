import styled from "styled-components";

const StyledTableRow = styled.div`
	margin: 20px 0;
	display: grid;
	grid-template-columns: 
		minmax(150px, 22%)
		repeat(${props => props.cols - 1}, minmax(50px, 1fr))
		minmax(50px, 5%);
	${props => props.bodyRow &&
	`border: 1px solid #F3F3FB;
		border-radius: 6px;
		transition: all 0.2s ease;
		cursor: pointer;
		&:hover {
			background-color: #F3F3FB;
		}`
	}
`

const TableRow = (props) => {
	return (
		<StyledTableRow {...props}>
			{props.children}
		</StyledTableRow>
	)
}

export default TableRow;