import { useState, useEffect } from 'react';
import TableBody from './TableBody'
import TableHeader from './TableHeader'
import styled from "styled-components";

const TableDeleta = (props) => {
	return (
		<div>123</div>
	)
}

const StyledTable = styled.div`
	min-width: 100%;
`

const Table = ({ data }) => {
	const [isDataProcessed, setDataProcessed] = useState(false);

	const processingData = () => {
		const options = data.options;

		if (options.delete) {
			data.header.delete = 'Опции';
			data.body.forEach(el => {
				el.delete = <TableDeleta />;
			});
		}
		setDataProcessed(true);
	}
	
	useEffect(() => {
		processingData();
	}, [])

	return (
		isDataProcessed &&
		<StyledTable>
			<TableHeader data={data.header} order={data.order} />
			<TableBody data={data.body} order={data.order} />
		</StyledTable>
	);
}

export default Table;