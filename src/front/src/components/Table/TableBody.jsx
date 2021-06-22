import TableRow from './TableRow';
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


const TableBody = ({ data, order }) => {
	return (
		<div>
			{
				data.map((_, dataIndex) => {
					return (
						<TableRow
							bodyRow={true}
							cols={order.length}
							key={dataIndex}
							bordercolor='#F3F3FB'
						>
							{
								order.map((el, i) => {
									return <TabeBodyCell key={i}>{data[dataIndex][el]}</TabeBodyCell>
								})
							}
						</TableRow>
					)
				})
			}
		</div>
	)
}

export default TableBody;