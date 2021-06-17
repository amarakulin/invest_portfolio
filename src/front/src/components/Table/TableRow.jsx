import styled from "styled-components";

const StyledTableRow = styled.div`
	margin: 20px 0;
	display: grid;
	grid-template-columns: 
		minmax(150px, 22%)
		repeat(auto-fit, minmax(100px, 1fr))
`

export const TableBodyRow = styled(StyledTableRow)`
	border: 1px solid #F3F3FB;
	border-radius: 6px;
	transition: all 0.2s ease;
	cursor: pointer;
	&:hover {
		background-color: #F3F3FB;
	}
`

const TableRow = (props) => {
	return (
		<StyledTableRow>
			{props.children}
		</StyledTableRow>
	)
}

export default TableRow;