import TableBody from './TableBody'
import TableHeader from './TableHeader'
import styled from "styled-components";

const StyledTable = styled.table`
	display: grid;
	min-width: 100%;
	border-collapse: collapse;
	grid-template-columns: 
		minmax(150px, 22%)
		minmax(150px, 14%)
		minmax(150px, 14%)
		minmax(150px, 14%)
		minmax(150px, 12%)
		minmax(150px, 12%)
		minmax(150px, 12%);
`

const Table = ({data}) => {
	return (
		<StyledTable>
			<TableHeader data={data.header}/>
			<TableBody data={data.body}/>
		</StyledTable>

	);
}

export default Table;