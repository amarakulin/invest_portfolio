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

const TableHeader = ({ data, order }) => {
	return (
		<TableRow cols={order.length}>
			{
				order.map((el, i) => {
					return <TableHeaderCell key={i}>{data[el]}</TableHeaderCell>
				})
			}
		</TableRow>
	)
}

export default TableHeader;