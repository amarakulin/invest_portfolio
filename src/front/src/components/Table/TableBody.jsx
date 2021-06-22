import { useState } from 'react';
import TableRow from './TableRow';
import styled from "styled-components";
import TableOptions from './TableOptons/TableOptions';
import EditAssetAmountForm from '../Forms/EditAssetAmountForm/EditAssetAmountForm'

const TabeBodyCell = styled.div`
	padding: 20px 12px 20px 12px;
	font-style: normal;
	font-weight: normal;
	font-size: 14px;
	color: #272833;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	display: flex;
	align-items: center;
	${props => {
		return props.options && 'justify-content: center; padding: 0 12px;'
	}}
`

const TableBody = ({ data, order }) => {
	const [editModeByTicker, setEditMode] = useState(null);

	return (
		<div>
			{
				data.map((_, dataIndex) => {
					return (
						<TableRow
							bodyRow={true}
							cols={order.length}
							key={data[dataIndex].ticker}
							bordercolor='#F3F3FB'
						>
							{
								order.map((el, i) => {
									return (
										<TabeBodyCell key={i}>
											{
												editModeByTicker === data[dataIndex].ticker && el === 'amount'
													? <EditAssetAmountForm 
														value={data[dataIndex][el]}
														setEditMode={setEditMode}
														ticker={data[dataIndex].ticker}
													/>
													: data[dataIndex][el]
											}
										</TabeBodyCell>
									)
								})
							}
							{
								<TableOptions
									setEditMode={setEditMode}
									ticker={data[dataIndex].ticker}
								/>
							}
						</TableRow>
					)
				})
			}
		</div>
	)
}

export default TableBody;