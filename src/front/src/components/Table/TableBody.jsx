import { TableBodyRow } from './TableRow';
import styled from "styled-components";

const TabeBodyCell = styled.div`
	padding: 20px 12px 20px 12px;
	font-style: normal;
	font-weight: normal;
	font-size: 14px;
	color: #272833;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
`


const TableBody = ({ data }) => {
	return (
		<div>
			{
				data.map((el, i) => {
					return (
						<TableBodyRow
							key={i}
							bordercolor='#F3F3FB'

						>
							{
								el.map((el, i) => {
									return <TabeBodyCell key={i}>{el}</TabeBodyCell>
								})
							}
						</TableBodyRow>
					)
				})
			}
		</div>
	)
}

export default TableBody;