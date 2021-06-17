import TableRow from './TableRow';
import styled from "styled-components";

const TableHeaderCell = styled.div`
	padding: 12px;
	font-style: normal;
	font-weight: 600;
	font-size: 16px;
	line-height: 150%;
	color: #272833;
`

const TableHeader = ({data}) => {
	return (
		<div>
			<TableRow>
				{
					data.map((el, i) => {
						return <TableHeaderCell key={i}>{el}</TableHeaderCell>
					})
				}
			</TableRow>
		</div>
	)
}

export default TableHeader;